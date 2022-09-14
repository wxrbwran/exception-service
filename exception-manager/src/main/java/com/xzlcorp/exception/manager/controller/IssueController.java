package com.xzlcorp.exception.manager.controller;

import com.xzlcorp.exception.common.common.ApiRestResponse;
import com.xzlcorp.exception.common.utils.PageInfoReducer.PageInfoReduce;
import com.xzlcorp.exception.manager.model.pojo.Issue;
import com.xzlcorp.exception.manager.model.query.IssueQuery;
import com.xzlcorp.exception.manager.model.query.IssuesTrendQuery;
import com.xzlcorp.exception.manager.model.vo.IssueVO;
import com.xzlcorp.exception.manager.service.IssueService;
import java.util.List;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author wuxiaoran
 */
@RestController
@RequestMapping("issues")
@Slf4j
public class IssueController {

  @Autowired
  private IssueService issueService;

  @GetMapping()
  public ApiRestResponse getIssues(IssueQuery query) {
    PageInfoReduce<Issue> issueList = issueService.getIssues(query);
    List<IssueVO> issueVOList = issueService.handleIssueToVO(issueList.getList());
    PageInfoReduce<IssueVO> pageInfoReduce = new PageInfoReduce<>();
    pageInfoReduce.setList(issueVOList);
    pageInfoReduce.setTotal(issueList.getTotal());
    return ApiRestResponse.success(pageInfoReduce);
  }
  @GetMapping("{issueId}")
  public ApiRestResponse getIssueById(@PathVariable Integer issueId) {
    Issue issue = issueService.getIssueById(issueId);
    return ApiRestResponse.success(issue);
  }

  @GetMapping("{projectId}/trend")
  public ApiRestResponse getProjectTrend(
      @PathVariable("projectId") Integer projectId,
      @RequestParam("start") long start,
      @RequestParam("end") long end
  ) {
    Map<String, Object> resMap = issueService.getIssuesProjectTrend(projectId, start, end);
    return ApiRestResponse.success(resMap);
  }

  @PostMapping("/trend")
  public ApiRestResponse getTrendByIssueIds(@RequestBody IssuesTrendQuery query) {
    List<Map<String, Object>> lists = issueService.getTrendByIssueIds(query);
    return ApiRestResponse.success(lists);
//    return ApiRestResponse.success();
  }
  @GetMapping("/event/latest")
  public ApiRestResponse getLatestEventByIssueId(@RequestParam Integer issueId) {
    log.info("issueId, {}", issueId);
    return ApiRestResponse.success(issueService.getLatestEventByIssueId(issueId));
  }

  @GetMapping("/event/{id}")
  public ApiRestResponse getEventByIdAndIssueId(@RequestParam Integer issueId, @PathVariable("id") Integer eventId) {
    log.info("issueId, {}", issueId);
    log.info("eventId, {}", eventId);

    return ApiRestResponse.success();
  }
}
