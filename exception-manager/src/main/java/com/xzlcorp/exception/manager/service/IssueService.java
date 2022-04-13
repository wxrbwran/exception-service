package com.xzlcorp.exception.manager.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xzlcorp.exception.common.utils.PageInfoReducer;
import com.xzlcorp.exception.manager.model.pojo.Issue;
import com.xzlcorp.exception.manager.model.query.IssueQuery;
import com.xzlcorp.exception.manager.model.request.CreateOrUpdateIssueByIntroRequest;
import com.xzlcorp.exception.manager.model.vo.IssueVO;

import java.util.List;

/**
* @author wxr
* @description 针对表【t_issue】的数据库操作Service
* @createDate 2022-04-12 22:59:00
*/
public interface IssueService extends IService<Issue> {

  PageInfoReducer.PageInfoReduce<Issue> getIssues(IssueQuery query);

  List<IssueVO> handleIssueToVO(List<Issue> issueList);

  Issue updateIssueByIntro(CreateOrUpdateIssueByIntroRequest request);

  Issue createIssueByIntro(CreateOrUpdateIssueByIntroRequest request);
}
