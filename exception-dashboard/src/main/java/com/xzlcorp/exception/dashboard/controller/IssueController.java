package com.xzlcorp.exception.dashboard.controller;

import com.xzlcorp.exception.dashboard.service.IssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wuxiaoran
 */
@RestController
@RequestMapping("issues")

public class IssueController {
  @Autowired
  private IssueService issueService;

//  @GetMapping()
//  public ApiRestResponse getAll(IssueQuery query) {
//    List<Issue> issueList = issueService.searchIssues(query);
//    List<IssueVO> issueVOList = issueService.handleIssueToVO(issueList);
//    return ApiRestResponse.success(issueVOList);
//  }
}
