package com.xzlcorp.exception.transfer.feign;

import com.xzlcorp.exception.common.model.pojo.event.Event;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author wuxiaoran
 */
@FeignClient(value="exception-manager")
public interface ManagerFeignClient {

  @PostMapping("events")
  void handleTransferEvent(@RequestBody Event event);
}
