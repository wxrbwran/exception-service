package com.xzlcorp.exception.manager.service;

import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.xzlcorp.exception.common.common.Constant;
import com.xzlcorp.exception.common.enums.EventIndicesEnum;
import com.xzlcorp.exception.common.enums.OhbugEventIndicesEnum;
import com.xzlcorp.exception.common.model.pojo.event.Event;
import com.xzlcorp.exception.common.utils.ArrayListUtils;
import com.xzlcorp.exception.common.utils.PageInfoReducer;
import com.xzlcorp.exception.common.utils.PageInfoReducer.PageInfoReduce;
import com.xzlcorp.exception.manager.enums.IssueTrendPeriodEnum;
import com.xzlcorp.exception.manager.feign.DashboardClient;
import com.xzlcorp.exception.manager.model.bo.BugDocument;
import com.xzlcorp.exception.manager.model.dao.IssueDynamicSqlSupport;
import com.xzlcorp.exception.manager.model.dao.IssueMapper;
import com.xzlcorp.exception.manager.model.pojo.Issue;
import com.xzlcorp.exception.manager.model.query.IssueQuery;
import com.xzlcorp.exception.manager.model.query.IssuesTrendQuery;
import com.xzlcorp.exception.manager.model.request.CreateOrUpdateIssueByIntroRequest;
import com.xzlcorp.exception.manager.model.vo.IssueVO;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.histogram.DateHistogramInterval;
import org.elasticsearch.search.aggregations.bucket.histogram.ExtendedBounds;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.joda.time.LocalDateTime;
import org.mybatis.dynamic.sql.SqlBuilder;
import org.mybatis.dynamic.sql.render.RenderingStrategies;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.util.*;
import java.util.stream.Stream;

import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

/**
 * @author wuxiaoran
 */
@Service
@Slf4j
public class IssueService {
  @Autowired
  private IssueMapper issueMapper;

  @Autowired
  private DashboardClient dashboardClient;

  @Autowired
  private RestHighLevelClient highLevelClient;

  private final static String Agg_Name_Trend = "trend";
  private final static String Format_Of_14d = "yyyy-MM-dd";
  private final static String Format_Of_24h = "yyyy-MM-dd HH";

  public PageInfoReduce<Issue> getIssues(IssueQuery query) {
    PageHelper.startPage(query.getPageAt(), query.getPageSize());
    String apiKey = dashboardClient.getApiKeyByProjectId(query.getProjectId());
    log.info("getIssues apiKey: {}", apiKey);
    SelectStatementProvider provider = SqlBuilder.select(IssueMapper.selectList)
        .from(IssueDynamicSqlSupport.issue)
        .where(IssueDynamicSqlSupport.apiKey, isEqualTo(apiKey))
//        .and(IssueDynamicSqlSupport.updatedAt, isBetween(LocalDate.of(2020, 1, 1)).and(LocalDate.now()))
        .orderBy(IssueDynamicSqlSupport.id.descending())
        .build()
        .render(RenderingStrategies.MYBATIS3);
    List<Issue> issues = issueMapper.selectMany(provider);
    PageInfoReduce<Issue> pageInfoReduce = PageInfoReducer.reduce(issues);
    return pageInfoReduce;
  }

  public List<IssueVO> handleIssueToVO(List<Issue> issueList) {
    List<IssueVO> issueVOList = new ArrayList<>();
    issueList.forEach(issue -> {
      IssueVO issueVO = new IssueVO();
      BeanUtils.copyProperties(issue, issueVO);
      issueVOList.add(issueVO);
    });
    return issueVOList;
  }

  private SearchResponse getTrend(QueryBuilder query, AggregationBuilder trend) {
    SearchRequest request = new SearchRequest();
    request.indices(OhbugEventIndicesEnum.ERROR.getKey());
    SearchSourceBuilder builder = SearchSourceBuilder.searchSource()
        .query(query)
        .aggregation(trend)
//        .sort(new FieldSortBuilder(PREDEFINED.TIME_ID).order(SortOrder.DESC))
        .size(0);
    log.info(" 条件查询 ： {}", builder);
    request.source(builder);
    try {
      SearchResponse response = highLevelClient.search(request, RequestOptions.DEFAULT);
      log.info("response json, {}", JSON.toJSONString(response));
//      response.getAggregations().get(Agg_Name_Trend)
      return response;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return  null;
  }

  private Map<String, QueryBuilder> getQueryMap(Date now, String issueId) {
    long start14d = DateUtil.offsetDay(now, -13).toTimestamp().getTime();
    long start24h = DateUtil.offsetHour(now, -23).toTimestamp().getTime();

    long end = now.getTime();

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
    queryMap.put("14d", queryOf14d);
    queryMap.put("24h", queryOf24h);

    return queryMap;
  }

  private Map<String, AggregationBuilder> getTrendMap(Date now) {
    Map<String, AggregationBuilder> trendMap = new HashMap<>();

    long start14d = DateUtil.offsetDay(now, -13).toTimestamp().getTime();
    long start24h = DateUtil.offsetHour(now, -23).toTimestamp().getTime();

    long end = now.getTime();

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
    String today = DateUtil.today();
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
    trendMap.put("14d", trendOf14d);
    trendMap.put("24h", trendOf24h);
    return trendMap;
  }

  public SearchResponse getTrendByIssueId(Date now, String issueId, String period) {
    Map<String, AggregationBuilder> trendMap = getTrendMap(now);
    Map<String, QueryBuilder> queryMap = getQueryMap(now, issueId);
    SearchResponse response = new SearchResponse();
    switch (period) {
      case Constant.TWO_WEEK:
        response = getTrend(queryMap.get(Constant.TWO_WEEK), trendMap.get(Constant.TWO_WEEK));
        break;
      case Constant.ONE_DAY:
        response = getTrend(queryMap.get(Constant.ONE_DAY), trendMap.get(Constant.ONE_DAY));
        break;
    }
    return response;
  }

  public List<SearchResponse> getTrendByIssueIds(IssuesTrendQuery query) {
    Date now = new Date();
    List<SearchResponse> list = new ArrayList<>();
    Stream.of(query.getIds()).forEach(issueId -> {
      SearchResponse response = getTrendByIssueId(now, issueId, query.getPeriod());
      list.add(response);
    });
    return list;
  }

  public SearchResponse getIssuesProjectTrend(Integer projectId, long start, long end) {
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
    SearchResponse response = getTrend(query, trend);
    return  response;
  }

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

      issueMapper.update(c ->
          c.set(IssueDynamicSqlSupport.eventsCount).equalTo(issue.getEventsCount())
              .set(IssueDynamicSqlSupport.events).equalTo(issue.getEvents())
              .set(IssueDynamicSqlSupport.users).equalTo(issue.getUsers())
              .set(IssueDynamicSqlSupport.usersCount).equalTo(issue.getUsersCount())
              .set(IssueDynamicSqlSupport.updatedAt).equalTo(new Date())
              .where(IssueDynamicSqlSupport.id, isEqualTo(issue.getId()))
      );
    }

    return issue;
  }

  public Issue createIssueByIntro(CreateOrUpdateIssueByIntroRequest request) {
    log.info("createIssueByIntro, 1");

    Event event = request.getEvent();
      SelectStatementProvider provider = SqlBuilder.select(IssueMapper.selectList)
          .from(IssueDynamicSqlSupport.issue)
          .where(IssueDynamicSqlSupport.intro, isEqualTo(request.getIntro()))
          .build().render(RenderingStrategies.MYBATIS3);
    Issue issue = issueMapper.selectOne(provider);
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

      issueMapper.insertSelective(issue);
    }
    return issue;
  }

}
