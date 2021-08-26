package com.xzlcorp.exception.dashboard.service;

import com.xzlcorp.exception.dashboard.feign.ManagerFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author wuxiaoran
 */
@Service
public class IssueService {

  @Autowired
  private ProjectService projectService;

  @Autowired
  private ManagerFeignClient managerFeignClient;

//  public List<Issue> searchIssues(IssueQuery query) {
//    Project project = projectService.getProjectById(query.getProjectId());
//    String apiKey = project.getApiKey();
//    query.setApiKey(apiKey);
//    List<Issue> issueVOList = managerFeignClient.searchIssuesForDashboard(query);
//    return issueVOList;
//  }
//
//  public List<IssueVO> handleIssueToVO(List<Issue> issueList) {
//    List<IssueVO> issueVOList = new ArrayList<>();
//    issueList.forEach(issue -> {
//      IssueVO issueVO = new IssueVO();
//      BeanUtils.copyProperties(issue, issueVO);
//      issueVOList.add(issueVO);
//    });
//    return issueVOList;
//  }
}
