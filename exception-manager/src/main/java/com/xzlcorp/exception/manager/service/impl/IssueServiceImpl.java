package com.xzlcorp.exception.manager.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xzlcorp.exception.common.common.Constant;
import com.xzlcorp.exception.common.enums.OhbugEventIndicesEnum;
import com.xzlcorp.exception.common.model.pojo.event.Event;
import com.xzlcorp.exception.common.utils.ArrayListUtils;
import com.xzlcorp.exception.common.utils.PageInfoReducer;
import com.xzlcorp.exception.common.utils.RedisConstants;
import com.xzlcorp.exception.manager.feign.DashboardClient;
import com.xzlcorp.exception.manager.mapper.IssueMapper;
import com.xzlcorp.exception.manager.model.bo.BugDocument;
import com.xzlcorp.exception.manager.model.bo.EventLikeWithIssueId;
import com.xzlcorp.exception.manager.model.pojo.Issue;
import com.xzlcorp.exception.manager.model.query.IssueQuery;
import com.xzlcorp.exception.manager.model.query.IssuesTrendQuery;
import com.xzlcorp.exception.manager.model.request.CreateOrUpdateIssueByIntroRequest;
import com.xzlcorp.exception.manager.model.vo.IssueVO;
import com.xzlcorp.exception.manager.service.IssueService;
import com.xzlcorp.exception.manager.utils.CacheClient;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.histogram.DateHistogramInterval;
import org.elasticsearch.search.aggregations.bucket.histogram.ExtendedBounds;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
* @author wxr
* @description 针对表【t_issue】的数据库操作Service实现
* @createDate 2022-04-12 22:59:00
*/
@Service
@Slf4j
public class IssueServiceImpl extends ServiceImpl<IssueMapper, Issue> implements IssueService {

  @Autowired
  private IssueService issueService;

  @Autowired
  private CacheClient cacheClient;

  @Autowired
  private DashboardClient dashboardClient;

  @Autowired
  private RestHighLevelClient highLevelClient;

  private final static long One_Day_Mills = 86400000;
  private final static String Agg_Name_Trend = "trend";
  private final static String Format_Of_14d = "yyyy-MM-dd";
  private final static String Format_Of_24h = "yyyy-MM-dd HH:mm:ss";
  @Override
  // 分页服务前端react-query进行缓存
  public PageInfoReducer.PageInfoReduce<Issue> getIssues(IssueQuery query) {
//    PageHelper.startPage(query.getPageAt(), query.getPageSize());
    Page<Issue> page = new Page<>(query.getPageAt(), query.getPageSize());
    String apiKey = dashboardClient.getApiKeyByProjectId(query.getProjectId());
    log.info("getIssues apiKey: {}", apiKey);
    LambdaQueryWrapper<Issue> queryWrapper = new LambdaQueryWrapper<>();
    queryWrapper.eq(Issue::getApiKey, apiKey).orderByDesc(Issue::getId);
    issueService.page(page, queryWrapper);
    return (PageInfoReducer.PageInfoReduce<Issue>) PageInfoReducer
        .reduce(page.getRecords(), page.getTotal());
  }

  @Override
  public Issue getIssueById(Integer issueId) {
    LambdaQueryWrapper<Issue> queryWrapper = new LambdaQueryWrapper<>();
    queryWrapper.eq(Issue::getId, issueId);
    Issue issue = this.getOne(queryWrapper);
    return issue;
  }
  @Override
  public List<IssueVO> handleIssueToVO(List<Issue> issueList) {
    List<IssueVO> issueVOList = new ArrayList<>();
    issueList.forEach(issue -> {
      IssueVO issueVO = new IssueVO();
      BeanUtils.copyProperties(issue, issueVO);
      issueVOList.add(issueVO);
    });
    return issueVOList;
  }

  private Map<String, Object> getTrend(QueryBuilder query, AggregationBuilder trend, String issueId) {
    SearchRequest request = new SearchRequest();
    request.indices(OhbugEventIndicesEnum.ERROR.getKey());
    SearchSourceBuilder builder = SearchSourceBuilder.searchSource()
        .query(query)
        .aggregation(trend)
//        .sort(new FieldSortBuilder(PREDEFINED.TIME_ID).order(SortOrder.DESC))
        .size(0);
//    log.info(" 条件查询 ： {}", builder);
    request.source(builder);
    try {
      SearchResponse response = highLevelClient.search(request, RequestOptions.DEFAULT);
//      log.info("response json, {}", JSON.toJSONString(response));
      Aggregation aggregation = response.getAggregations().get(Agg_Name_Trend);
//      log.info("response stringAggregationMap, {}", aggregation);
      Map<String, Object> resMap = new HashMap<>();
      resMap.put("response", aggregation);
      if (issueId != null) {
        resMap.put("issueId", issueId);
      }
      return resMap;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return  null;
  }

  public Map<String, Object> getTrendBy14D(String issueId) {
    long now = System.currentTimeMillis();
    Map<String, AggregationBuilder> trendMap = getTrendMap(now);
    Map<String, QueryBuilder> queryMap = getQueryMap(now, issueId);
    return this.getTrend(queryMap.get(Constant.TWO_WEEK), trendMap.get(Constant.TWO_WEEK), issueId);
  }
  private Map<String, Object> getTrendBy1D(String issueId) {
    long now = System.currentTimeMillis();
    Map<String, AggregationBuilder> trendMap = getTrendMap(now);
    Map<String, QueryBuilder> queryMap = getQueryMap(now, issueId);
    return this.getTrend(queryMap.get(Constant.ONE_DAY), trendMap.get(Constant.ONE_DAY), issueId);
  }
  private Map<String, QueryBuilder> getQueryMap(long now, String issueId) {
    long start14d = now - (One_Day_Mills * 14);
    long start24h = now - One_Day_Mills;
    long end = now;
    Map<String, QueryBuilder> queryMap = new HashMap<>();
    QueryBuilder queryOf14d = QueryBuilders.boolQuery()
        .must(QueryBuilders.matchQuery("issueId", issueId))
        .filter(QueryBuilders.rangeQuery("event.timestamp")
            .gte(start14d)
            .lte(end)
        );
    QueryBuilder queryOf24h = QueryBuilders.boolQuery()
        .must(QueryBuilders.matchQuery("issueId", issueId))
        .filter(QueryBuilders.rangeQuery("event.timestamp")
            .gte(start24h)
            .lte(end)
        );
    queryMap.put(Constant.TWO_WEEK, queryOf14d);
    queryMap.put(Constant.ONE_DAY, queryOf24h);

    return queryMap;
  }

  private Map<String, AggregationBuilder> getTrendMap(long now) {
    Map<String, AggregationBuilder> trendMap = new HashMap<>();

    long start14d = now - (One_Day_Mills * 14);
    long start24h = now - One_Day_Mills;

    long end = now;

    AggregationBuilder trendOf14d = AggregationBuilders
        .dateHistogram(Agg_Name_Trend)
        .field("event.timestamp")
        .calendarInterval(DateHistogramInterval.DAY)
        .format(Format_Of_14d)
        .minDocCount(0)
        .extendedBounds(new ExtendedBounds(
            start14d,
            end
        ));
    AggregationBuilder trendOf24h = AggregationBuilders
        .dateHistogram(Agg_Name_Trend)
        .field("event.timestamp")
        .minDocCount(0)
        .calendarInterval(DateHistogramInterval.HOUR)
        .format(Format_Of_24h)
        .extendedBounds(new ExtendedBounds(
            start24h,
            end
        ));
    trendMap.put(Constant.TWO_WEEK, trendOf14d);
    trendMap.put(Constant.ONE_DAY, trendOf24h);
    return trendMap;
  }
  @Override
  public Map<String, Object> getTrendByIssueId(String issueId, String period) {
    Map<String, Object> resMap = new HashMap<>();
    switch (period) {
      case Constant.TWO_WEEK:
        // 互斥锁解决缓存击穿
        resMap = cacheClient
            .queryWithMutex(RedisConstants.ISSUE_TREND_14D + issueId,
                String.valueOf(issueId),
                Map.class, this::getTrendBy14D,
                RedisConstants.CACHE_ISSUE_TREND_TTL, TimeUnit.HOURS,
                RedisConstants.LOCK_ISSUE_TREND_14D_KEY + issueId);
        break;
      case Constant.ONE_DAY:
        resMap = cacheClient
            .queryWithMutex(RedisConstants.ISSUE_TREND_1D + issueId, String.valueOf(issueId),
                Map.class, this::getTrendBy1D,
                RedisConstants.CACHE_ISSUE_TREND_TTL, TimeUnit.HOURS,
                RedisConstants.LOCK_ISSUE_TREND_1D_KEY + issueId);
        break;
    }
    return resMap;
  }

  @Override
  public List<Map<String, Object>> getTrendByIssueIds(IssuesTrendQuery query) {
    List<Map<String, Object>> list = new ArrayList<>();
    if (query.getPeriod().equals(Constant.ALL)) {
      Stream.of(query.getIds()).forEach(issueId -> {
        Map<String, Object> resMap = getTrendByIssueId(issueId, Constant.ONE_DAY);
        Map<String, Object> oneDayRes = new HashMap<>();
        oneDayRes.put(Constant.ONE_DAY, resMap);
        list.add(oneDayRes);
      });
      Stream.of(query.getIds()).forEach(issueId -> {
        Map<String, Object> resMap = getTrendByIssueId(issueId, Constant.TWO_WEEK);
        Map<String, Object> twoWeekRes = new HashMap<>();
        twoWeekRes.put(Constant.TWO_WEEK, resMap);
        list.add(twoWeekRes);
      });
    } else {
      Stream.of(query.getIds()).forEach(issueId -> {
        Map<String, Object> resMap = getTrendByIssueId(issueId, query.getPeriod());
        list.add(resMap);
      });
    }
    return list;
  }

  @Override
  public Map<String, Object> getTrendByIssueIdsAll(IssuesTrendQuery query) {
    Map<String, Object> allRes = new HashMap<>();
    List<Object> listOneDay = new ArrayList<>();
    Stream.of(query.getIds()).forEach(issueId -> {
      Map<String, Object> resMap = getTrendByIssueId(issueId, Constant.ONE_DAY);
//      Map<String, Object> oneDayRes = new HashMap<>();
      listOneDay.add(resMap);
    });
    allRes.put(Constant.ONE_DAY, listOneDay);

    List<Object> listTwoWeek = new ArrayList<>();
    Stream.of(query.getIds()).forEach(issueId -> {
      Map<String, Object> resMap = getTrendByIssueId(issueId, Constant.TWO_WEEK);
//      Map<String, Object> twoWeekRes = new HashMap<>();
      listTwoWeek.add(resMap);
    });
    allRes.put(Constant.TWO_WEEK, listTwoWeek);
    return allRes;
  }

  @Override
  public Map<String, Object> getIssuesProjectTrend(Integer projectId, long start, long end) {
    // 从es中查询趋势
    String apiKey = dashboardClient.getApiKeyByProjectId(projectId);
    log.info("getIssuesProjectTrend, apiKey: {}", apiKey);
    long timeDiffInHours = (end - start) / 1000 / 60 /60;
    long twoWeekHours = 312;
    ExtendedBounds extendedBounds = new ExtendedBounds(start, end);
    QueryBuilder query = QueryBuilders.boolQuery()
        .must(QueryBuilders.matchQuery("event.apiKey", apiKey))
        .filter(QueryBuilders.rangeQuery("event.timestamp")
            .gte(start).lte(end)
        );
    AggregationBuilder trend = AggregationBuilders
        .dateHistogram(Agg_Name_Trend)
        .field("event.timestamp")
        .minDocCount(0)
        .calendarInterval(timeDiffInHours >= twoWeekHours ?
            DateHistogramInterval.DAY : DateHistogramInterval.HOUR)
        .extendedBounds(extendedBounds)
        .subAggregation(AggregationBuilders
            .cardinality("distinct")
            .field("issueId")
        );

    Map<String, Object> resMap = getTrend(query, trend, null);
    return  resMap;
  }

  @Override
  public Issue updateIssueByIntro(CreateOrUpdateIssueByIntroRequest request) {
    log.info("updateIssueByIntro, 2");

    Issue issue = request.getBaseIssue();
    Event event = request.getEvent();
    // 已经存在
    Integer[] users = issue.getUsers();
    List<Integer> usersList = ArrayListUtils.toList(users);
    // users 最多存储 1000，超过后只更改 usersCount
    if (usersList.size() < Constant.MAX_USERS_NUMBER) {
      Integer userId = Integer.parseInt(event.getUser().getId());
      if (!usersList.contains(userId)) {
        usersList.add(userId);
      }
      issue.setUsers(usersList.toArray(new Integer[usersList.size()]));
      issue.setUsersCount(usersList.size());
    } else {
      issue.setUsersCount(issue.getUsersCount() + 1);
    }

    if (request.getDocumentId() != null && request.getIndex() != null) {
      // 4. 更新event
      BugDocument document = new BugDocument();
      document.setDocumentId(request.getDocumentId());
      document.setIndex(request.getIndex());

      log.info("issue, {}", JSON.toJSONString(issue));

      String[] events = issue.getEvents();
      log.info("events, {}", issue.getEvents() == null);
      // events 最多存储 100 条，超过后只更改 eventsCount
      List<String> eventsList = ArrayListUtils.toList(events);
      if (events.length < Constant.MAX_ISSUES_NUMBER) {
        eventsList.add((document.getDocumentId()));
        issue.setEvents(eventsList.toArray(new String[eventsList.size()]));
        issue.setEventsCount(eventsList.size());
      } else {
        issue.setEventsCount(issue.getEventsCount() + 1);
      }
      log.info("issue.getEvents(), {}", issue.getEvents());
      LambdaQueryWrapper<Issue> queryWrapper = new LambdaQueryWrapper();
      Issue updateIssue = new Issue();
      updateIssue.setEventsCount(issue.getEventsCount());
      updateIssue.setEvents(issue.getEvents());
      updateIssue.setUsers(issue.getUsers());
      updateIssue.setUsersCount(issue.getUsersCount());
      updateIssue.setUpdatedAt(new Date());
      issueService.update(updateIssue, queryWrapper.eq(Issue::getId, issue.getId()));
//      issueMapper.update(c ->
//          c.set(IssueDynamicSqlSupport.eventsCount).equalTo(issue.getEventsCount())
//              .set(IssueDynamicSqlSupport.events).equalTo(issue.getEvents())
//              .set(IssueDynamicSqlSupport.users).equalTo(issue.getUsers())
//              .set(IssueDynamicSqlSupport.usersCount).equalTo(issue.getUsersCount())
//              .set(IssueDynamicSqlSupport.updatedAt).equalTo(new Date())
//              .where(IssueDynamicSqlSupport.id, isEqualTo(issue.getId()))
//      );
    }

    return issue;
  }

  @Override
  public Issue createIssueByIntro(CreateOrUpdateIssueByIntroRequest request) {
    log.info("createIssueByIntro, 1");

    Event event = request.getEvent();
    LambdaQueryWrapper<Issue> queryWrapper = new LambdaQueryWrapper<>();
    queryWrapper.eq(Issue::getIntro, request.getIntro());
//    SelectStatementProvider provider = SqlBuilder.select(IssueMapper.selectList)
//        .from(IssueDynamicSqlSupport.issue)
//        .where(IssueDynamicSqlSupport.intro, isEqualTo(request.getIntro()))
//        .build().render(RenderingStrategies.MYBATIS3);
    //    Issue issue = issueMapper.selectOne(provider);
    Issue issue = issueService.getOne(queryWrapper);
    if (issue == null) {
      // 不存在，创建
      issue = new Issue();
      issue.setIntro(request.getIntro());
      issue.setApiKey(event.getApiKey());
      issue.setMetadata(request.getMetaData());
      issue.setType(event.getType());
      issue.setEventsCount(0);
      issue.setEvents(new String[]{});
      issue.setUsers(new Integer[]{Integer.parseInt(event.getUser().getId())});
      issue.setUsersCount(1);
      issueService.save(issue);
    }
    return issue;
  }

  @Override
  public Event getLatestEventByIssueId(Integer issueId) {
    LambdaQueryWrapper<Issue> queryWrapper = new LambdaQueryWrapper<>();
    queryWrapper.eq(Issue::getId, issueId);
    Issue issue = issueService.getOne(queryWrapper);
    List<String> documentIds = Arrays.stream(issue.getEvents()).collect(Collectors.toList());
    String latestDocumentId = documentIds.get(documentIds.size() - 1);
    return getEventByIdAndIssueId(latestDocumentId, issueId, issue);
  }



  @Override
  public Event getEventByIdAndIssueId(String latestDocumentId, Integer issueId, Issue issue) {
    Issue curIssue = null;
    if (issue != null) {
      curIssue = issue;
    } else {
      LambdaQueryWrapper<Issue> queryWrapper = new LambdaQueryWrapper<>();
      queryWrapper.eq(Issue::getId, issueId);
      curIssue = issueService.getOne(queryWrapper);
    }
    List<String> documentIds = Arrays.stream(curIssue.getEvents()).collect(Collectors.toList());
    int eventIndex = documentIds.indexOf(latestDocumentId);
    String previousEvent = null;
    if (eventIndex - 1 >= 0) {
      previousEvent = documentIds.get(eventIndex - 1);
    }
    String nextEvent = null;
    if (eventIndex + 1 < documentIds.size()) {
      nextEvent = documentIds.get(eventIndex + 1);
    }
    GetRequest request = new GetRequest();
    String index = OhbugEventIndicesEnum.ERROR.getIndex();
    request.index(index).id(latestDocumentId);
    GetResponse response = null;
    try {
      response = highLevelClient.get(request, RequestOptions.DEFAULT);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    String sourceAsString = response.getSourceAsString();
    EventLikeWithIssueId eventLikeWithIssueId = JSON.parseObject(sourceAsString, EventLikeWithIssueId.class);
    Event event = eventLikeWithIssueId.getEvent();
    event.setIndex(index);
    event.setId(latestDocumentId);
    event.setPrevious(previousEvent);
    event.setNext(nextEvent);
    log.info("eventLikeWithIssueId, {}", eventLikeWithIssueId);
    return event;
  }
}




