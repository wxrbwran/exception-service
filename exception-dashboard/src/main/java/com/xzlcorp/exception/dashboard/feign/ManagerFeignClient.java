package com.xzlcorp.exception.dashboard.feign;

import com.xzlcorp.exception.common.common.ApiRestResponse;
import io.swagger.models.auth.In;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author wuxiaoran
 */
@FeignClient(value="exception-manager")
public interface ManagerFeignClient {
  @GetMapping("issues/getProjectTrendByApiKey")
  String getProjectTrendByApiKey(@RequestParam Integer projectId);

  @GetMapping("issues/event/latest")
  ApiRestResponse getLatestEventByIssueId(@RequestParam Integer issueId);

  @GetMapping("issues/event/{id}")
  ApiRestResponse getEventByIdAndEventId(@RequestParam Integer issueId, @PathVariable("id") Integer eventId);
}
