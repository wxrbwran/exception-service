package com.xzlcorp.exception.manager.service.impl;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xzlcorp.exception.common.common.BrowserError;
import com.xzlcorp.exception.common.enums.EventIndicesEnum;
import com.xzlcorp.exception.common.enums.OhbugEventIndicesEnum;
import com.xzlcorp.exception.common.model.pojo.event.Detail;
import com.xzlcorp.exception.common.model.pojo.event.DetailAjaxReq;
import com.xzlcorp.exception.common.model.pojo.event.Event;
import com.xzlcorp.exception.common.model.pojo.event.MetaData;
import com.xzlcorp.exception.common.utils.ESutils;
import com.xzlcorp.exception.common.utils.Md5Utils;
import com.xzlcorp.exception.manager.feign.DashboardClient;
import com.xzlcorp.exception.manager.model.bo.AggregationDataAndMetaData;
import com.xzlcorp.exception.manager.model.bo.EventLikeWithIssueId;
import com.xzlcorp.exception.manager.model.pojo.Document;
import com.xzlcorp.exception.manager.model.pojo.Issue;
import com.xzlcorp.exception.manager.model.request.CreateOrUpdateIssueByIntroRequest;
import com.xzlcorp.exception.manager.service.IssueService;
import lombok.extern.slf4j.Slf4j;

import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wuxiaoran
 */
@Service
@Slf4j
public class EventService {

  @Autowired
  private IssueService issueService;

  @Autowired
  private KafkaProducerService kafkaProducerService;

  @Autowired
  private DashboardClient dashboardClient;

  @Autowired
  private RestHighLevelClient highLevelClient;


  public void handleEvent(Event event) throws NoSuchAlgorithmException {
    // 0. 分析event，根据apiKey自增org的eventCount
    dashboardClient.increaseEventCount(event.getApiKey());
    // 1. 聚合
    CreateOrUpdateIssueByIntroRequest request =  aggregation(event);
    request.setEvent(event);
    log.info("CreateOrUpdateIssueByIntroRequest, request: {}", request);
    // 2. 创建issue （postgres）
    Issue baseIssue = issueService.createIssueByIntro(request);
    // 3. 创建events（elastic）
    EventIndicesEnum indicesEnum = ESutils.getIndexOrKeyByEvent(event);
    EventLikeWithIssueId eventLikeWithIssueId = new EventLikeWithIssueId();
    eventLikeWithIssueId.setEvent(event);
    eventLikeWithIssueId.setIssueId(baseIssue.getId());

    String topic = indicesEnum.getKey();
    String index = indicesEnum.getIndex();

    eventLikeWithIssueId.setKey(topic);


    // 新增文档 - 请求对象
    IndexRequest indexRequest = new IndexRequest();
    // 设置索引及唯一性标识
    String documentId = topic + "--" + LocalDateTime.now();
    log.info("documentId: {}", documentId);
    indexRequest.index(OhbugEventIndicesEnum.ERROR.getIndex()).id(documentId);

    ObjectMapper objectMapper = new ObjectMapper();
    String eventJson = null;
    try {
      eventJson = objectMapper.writeValueAsString(eventLikeWithIssueId);
    } catch (JsonProcessingException e) {
      e.printStackTrace();
    }
    // 添加文档数据，数据格式为 JSON 格式
    indexRequest.source(eventJson, XContentType.JSON);
    // 客户端发送请求，获取响应对象
    try {
      IndexResponse response = highLevelClient.index(indexRequest, RequestOptions.DEFAULT);
      System.out.println("_index:" + response.getIndex());
      System.out.println("_id:" + response.getId());
      System.out.println("_result:" + response.getResult());
    } catch (IOException e) {
      throw new RuntimeException("插入数据失败", e);
    }

//    ListenableFutureCallback<SendResult<String, String>> callback = new ListenableFutureCallback<>() {
//      @Override
//      public void onFailure(Throwable ex) {
//        log.error("发送消息失败：{}" , ex.getMessage());
//      }
//
//      @Override
//      public void onSuccess(SendResult<String, String> result) {


    log.info("消息发送成功, topic: {}", topic);


    // 4. 更新 issue 的 events (postgres)
    Document document = new Document();
    document.setDocumentId(documentId);
    document.setIndex(index);
    CreateOrUpdateIssueByIntroRequest introRequest = new CreateOrUpdateIssueByIntroRequest();
//        event, baseIssue, documentId, index
    introRequest.setEvent(event);
    introRequest.setBaseIssue(baseIssue);
    introRequest.setDocumentId(documentId);
    introRequest.setIndex(index);
    Issue issue = issueService.updateIssueByIntro(introRequest);
    // 5. 更新 organization 中的 count
    dashboardClient.increaseEventCount(event.getApiKey());
    // todo: 通知
    // 6. 根据 apiKey 拿到对应的 notification 配置

    // 7. 判断当前状态十分符合 notification 配置的要求，符合则通知 notifier 开始任务
//      }
//    };
//    log.info("发送的消息: {}", JSON.toJSONString(eventLikeWithIssueId));

//    kafkaProducerService.sendMessageWithCallback(topic, JSON.toJSONString(eventLikeWithIssueId), callback);

  }

  public CreateOrUpdateIssueByIntroRequest aggregation(Event event)
          throws NoSuchAlgorithmException {
    String type = event.getType();
    Detail detail = event.getDetail();
    String apiKey = event.getApiKey();
    AggregationDataAndMetaData<String> aggAndMetadata = switchErrorDetailAndGetAggregationDataAndMetaData(type, detail, apiKey);
    List<String> aggList = aggAndMetadata.getAgg();
//    String[] aggStrList = aggList.toArray(new String[aggList.size()]);
    String intro = Md5Utils.getMD5String(aggList.toString());

    CreateOrUpdateIssueByIntroRequest request = new CreateOrUpdateIssueByIntroRequest();
    request.setIntro(intro);
    request.setMetaData(aggAndMetadata.getMetaData());

    return request;
  }

  private AggregationDataAndMetaData<String> switchErrorDetailAndGetAggregationDataAndMetaData(String type, Detail detail, String apiKey) {
    AggregationDataAndMetaData<String> aggAndMetadata = new AggregationDataAndMetaData<>();
    List<String> aggList = new ArrayList<>();
    aggList.add(apiKey);

    MetaData metaData = new MetaData();
    metaData.setType(type);
    metaData.setMessage(detail.getMessage());
    switch (type) {
      case BrowserError.UNCAUGHT_ERROR:
        aggList.add(detail.getName());
        aggList.add(detail.getMessage());
        aggList.add(detail.getFilename());
        aggList.add(detail.getLineno());
        aggList.add(detail.getColno());

        metaData.setFilename(detail.getFilename());
        break;
      case BrowserError.UNHANDLEDREJECTION_ERROR:
      case BrowserError.UNKNOWN_ERROR:
        aggList.add(detail.getMessage());
        break;
      case BrowserError.RESOURCE_ERROR:
        aggList.add(detail.getOuterHTML());
        aggList.add(detail.getSrc());
        aggList.add(detail.getTagName());
        aggList.add(detail.getId());
        aggList.add(detail.getClassname());
        aggList.add(detail.getName());
        aggList.add(detail.getNodeType());
        aggList.add(detail.getSelector());

        metaData.setOthers(detail.getSelector());
        break;
      case BrowserError.AJAX_ERROR:
      case BrowserError.FETCH_ERROR:
        DetailAjaxReq req = detail.getReq();
        aggList.add(req.getUrl());
        aggList.add(req.getMethod());
        aggList.add(req.getData().toString());

        metaData.setOthers(req.getMethod());
        metaData.setMessage(req.getUrl());
        break;
      case BrowserError.WEBSOCKET_ERROR:
        aggList.add(detail.getUrl());
        metaData.setMessage(detail.getUrl());
        break;
      case BrowserError.REACT:
        aggList.add(detail.getName());
        aggList.add(detail.getMessage());
        aggList.add(detail.getErrorInfo());

        metaData.setOthers(detail.getErrorInfo());
        break;
      case BrowserError.VUE:
        aggList.add(detail.getName());
        aggList.add(detail.getMessage());
        aggList.add(detail.getErrorInfo());
        aggList.add(detail.getComponent());
        aggList.add(detail.getProps());
        aggList.add(detail.getFilename());

        metaData.setOthers(detail.getErrorInfo());
        metaData.setFilename(detail.getFile());
        break;
      case BrowserError.MINIAPP_ERROR:
      case BrowserError.MINIAPP_UNHANDLEDREJECTION_ERROR:
        aggList.add(detail.getMessage());
        if (detail.getStack() != null) {
          aggList.add(detail.getStack());
        }
        metaData.setStack(detail.getStack());
        break;
      case BrowserError.MINIAPP_PAGENOTFOUND_ERROR:
        aggList.add(detail.getMessage());
        aggList.add(detail.getPath());
        aggList.add(detail.getQuery());
        aggList.add(detail.getIsEntryPage().toString());

        metaData.setPath(detail.getPath());
        metaData.setQuery(detail.getQuery());
        break;
      case BrowserError.MINIAPP_MEMORYWARNING_ERROR:
        aggList.add(detail.getMessage());
        aggList.add(detail.getLevel());

        metaData.setLevel(detail.getLevel());
        break;
      default:
        aggList.add(detail.getMessage());
        break;
    }
    aggAndMetadata.setAgg(aggList);
    aggAndMetadata.setMetaData(metaData);
    return aggAndMetadata;
  }
}
