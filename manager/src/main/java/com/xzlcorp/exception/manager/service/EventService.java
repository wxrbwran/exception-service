package com.xzlcorp.exception.manager.service;

import com.xzlcorp.exception.common.common.BrowserError;
import com.xzlcorp.exception.common.model.pojo.event.Detail;
import com.xzlcorp.exception.common.model.pojo.event.DetailAjaxReq;
import com.xzlcorp.exception.common.model.pojo.event.Event;
import com.xzlcorp.exception.common.model.pojo.event.MetaData;
import com.xzlcorp.exception.common.utils.Md5Utils;
import com.xzlcorp.exception.manager.model.bo.AggregationDataAndMetaData;
import com.xzlcorp.exception.manager.model.pojo.Issue;
import com.xzlcorp.exception.manager.model.request.CreateOrUpdateIssueByIntroRequest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author wuxiaoran
 */
@Service
@Slf4j
public class EventService {

  @Autowired
  private IssueService issueService;


  public void handleEvent(Event event) throws NoSuchAlgorithmException {
    // 1. 聚合
    CreateOrUpdateIssueByIntroRequest request =  aggregation(event);
    request.setEvent(event);
    log.info("CreateOrUpdateIssueByIntroRequest, request: {}", request);
    // 2. 创建issue （postgres）
    Issue baseIssue = issueService.createOrUpdateIssueByIntro(request);
    // todo: 3. 创建events（elastic）

  }

  public CreateOrUpdateIssueByIntroRequest aggregation(Event event)
      throws NoSuchAlgorithmException {
    String type = event.getType();
    Detail detail = event.getDetail();
    String apiKey = event.getApiKey();
    AggregationDataAndMetaData aggAndMetadata = switchErrorDetailAndGetAggregationDataAndMetaData(type, detail, apiKey);
    List<String> aggList = aggAndMetadata.getAgg();
//    String[] aggStrList = aggList.toArray(new String[aggList.size()]);
    String intro = Md5Utils.getMD5String(aggList.toString());

    CreateOrUpdateIssueByIntroRequest request = new CreateOrUpdateIssueByIntroRequest();
    request.setIntro(intro);
    request.setMetaData(aggAndMetadata.getMetaData());

    return request;
  }

  private AggregationDataAndMetaData switchErrorDetailAndGetAggregationDataAndMetaData(String type, Detail detail, String apiKey) {
    AggregationDataAndMetaData aggAndMetadata = new AggregationDataAndMetaData();
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
