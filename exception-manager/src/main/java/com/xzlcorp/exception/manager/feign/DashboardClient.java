package com.xzlcorp.exception.manager.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author wuxiaoran
 */
@FeignClient("exception-dashboard")
public interface DashboardClient {

  @GetMapping("projects/getApiKeyByProjectId")
  String getApiKeyByProjectId(@RequestParam("projectId") Integer projectId);

  @GetMapping("organizations/event/increase")
  String increaseEventCount(@RequestParam("apiKey") String apiKey);
}
