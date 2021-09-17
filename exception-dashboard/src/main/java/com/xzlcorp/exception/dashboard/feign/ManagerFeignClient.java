package com.xzlcorp.exception.dashboard.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author wuxiaoran
 */
@FeignClient(value="exception-manager")
public interface ManagerFeignClient {
  @GetMapping("issues/getProjectTrendByApiKey")
  String getProjectTrendByApiKey(@RequestParam Integer projectId);
}
