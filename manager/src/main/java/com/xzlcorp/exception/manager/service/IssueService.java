package com.xzlcorp.exception.manager.service;

import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

import com.xzlcorp.exception.common.common.Constant;
import com.xzlcorp.exception.common.model.pojo.event.Event;
import com.xzlcorp.exception.common.utils.ArrayListUtils;
import com.xzlcorp.exception.manager.feign.DashboardClient;
import com.xzlcorp.exception.manager.model.bo.BugDocument;
import com.xzlcorp.exception.manager.model.query.IssueQuery;
import com.xzlcorp.exception.manager.model.dao.IssueDynamicSqlSupport;
import com.xzlcorp.exception.manager.model.dao.IssueMapper;
import com.xzlcorp.exception.manager.model.pojo.Issue;
import com.xzlcorp.exception.manager.model.request.CreateOrUpdateIssueByIntroRequest;
import com.xzlcorp.exception.manager.model.vo.IssueVO;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.dynamic.sql.SqlBuilder;
import org.mybatis.dynamic.sql.render.RenderingStrategies;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

  public List<Issue> getIssues(IssueQuery query) {
    String apiKey = dashboardClient.getApiKeyByProjectId(query.getProjectId());
    log.info("getIssues apiKey: {}", apiKey);
    SelectStatementProvider provider = SqlBuilder.select(IssueMapper.selectList)
        .from(IssueDynamicSqlSupport.issue)
        .where(IssueDynamicSqlSupport.apiKey, isEqualTo(apiKey))
        .build()
        .render(RenderingStrategies.MYBATIS3);
    List<Issue> issues = issueMapper.selectMany(provider);
    return issues;
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

  public Issue createOrUpdateIssueByIntro(CreateOrUpdateIssueByIntroRequest request) {
    Issue issue = request.getBaseIssue();
    Event event = request.getEvent();
    if (issue == null) {
      SelectStatementProvider provider = SqlBuilder.select(IssueMapper.selectList)
          .from(IssueDynamicSqlSupport.issue)
          .where(IssueDynamicSqlSupport.intro, isEqualTo(request.getIntro()))
          .build().render(RenderingStrategies.MYBATIS3);
      issue = issueMapper.selectOne(provider);
    }
    if (issue == null) {
      // 不存在，创建
      issue = new Issue();
      issue.setIntro(request.getIntro());
      issue.setApiKey(event.getApiKey());
      issue.setMetadata(request.getMetaData());
      issue.setType(event.getType());
      issue.setEventsCount(1);
      issue.setUsers(new Integer[]{Integer.parseInt(event.getUser().getId())});
      issue.setUsersCount(1);

      issueMapper.insertSelective(issue);
    } else {
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
        // todo: 4. 更新event
        BugDocument document = new BugDocument();
        document.setDocumentId(request.getDocumentId());
        document.setIndex(request.getIndex());
        Integer[] events = issue.getEvents();
        // events 最多存储 100 条，超过后只更改 eventsCount
        List<Integer> eventsList = ArrayListUtils.toList(events);
        if (events.length < Constant.MAX_ISSUES_NUMBER) {
          eventsList.add(Integer.parseInt(document.getDocumentId()));
          issue.setEvents(eventsList.toArray(new Integer[eventsList.size()]));
          issue.setEventsCount(eventsList.size() + 1);
        } else {
          issue.setEventsCount(eventsList.size() + 1);
        }
      }
      issueMapper.updateByPrimaryKeySelective(issue);
      return issue;
    }
    return issue;
  }
}
