package com.xzlcorp.exception.manager.service;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.xzlcorp.exception.common.common.Constant;
import com.xzlcorp.exception.common.model.pojo.event.Event;
import com.xzlcorp.exception.common.utils.ArrayListUtils;
import com.xzlcorp.exception.common.utils.PageInfoReducer;
import com.xzlcorp.exception.common.utils.PageInfoReducer.PageInfoReduce;
import com.xzlcorp.exception.manager.feign.DashboardClient;
import com.xzlcorp.exception.manager.model.bo.BugDocument;
import com.xzlcorp.exception.manager.model.dao.IssueDynamicSqlSupport;
import com.xzlcorp.exception.manager.model.dao.IssueMapper;
import com.xzlcorp.exception.manager.model.pojo.Issue;
import com.xzlcorp.exception.manager.model.query.IssueQuery;
import com.xzlcorp.exception.manager.model.request.CreateOrUpdateIssueByIntroRequest;
import com.xzlcorp.exception.manager.model.vo.IssueVO;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.dynamic.sql.SqlBuilder;
import org.mybatis.dynamic.sql.render.RenderingStrategies;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

  public void getIssuesProjectTrend(Integer projectId, long start, long end) {
    String apiKey = dashboardClient.getApiKeyByProjectId(projectId);
    // todo：从es中查询趋势
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
