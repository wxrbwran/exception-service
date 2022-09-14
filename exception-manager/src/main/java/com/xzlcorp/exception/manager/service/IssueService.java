package com.xzlcorp.exception.manager.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xzlcorp.exception.common.model.pojo.event.Event;
import com.xzlcorp.exception.common.utils.PageInfoReducer;
import com.xzlcorp.exception.manager.model.pojo.Issue;
import com.xzlcorp.exception.manager.model.query.IssueQuery;
import com.xzlcorp.exception.manager.model.query.IssuesTrendQuery;
import com.xzlcorp.exception.manager.model.request.CreateOrUpdateIssueByIntroRequest;
import com.xzlcorp.exception.manager.model.vo.IssueVO;

import java.util.List;
import java.util.Map;

/**
* @author wxr
* @description 针对表【t_issue】的数据库操作Service
* @createDate 2022-04-12 22:59:00
*/
public interface IssueService extends IService<Issue> {

  PageInfoReducer.PageInfoReduce<Issue> getIssues(IssueQuery query);

  List<IssueVO> handleIssueToVO(List<Issue> issueList);

  Map<String, Object> getTrendByIssueId(String issueId, String period);

  List<Map<String, Object>> getTrendByIssueIds(IssuesTrendQuery query);

  Map<String, Object> getTrendByIssueIdsAll(IssuesTrendQuery query);

  Map<String, Object> getIssuesProjectTrend(Integer projectId, long start, long end);

  Issue updateIssueByIntro(CreateOrUpdateIssueByIntroRequest request);

  Issue createIssueByIntro(CreateOrUpdateIssueByIntroRequest request);

  Event getLatestEventByIssueId(Integer issueId);

  Issue getIssueById(Integer issueId);
}
