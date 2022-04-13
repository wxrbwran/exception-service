package com.xzlcorp.exception.transfer.controller;

import com.xzlcorp.exception.common.common.ApiRestResponse;
import com.xzlcorp.exception.common.model.pojo.event.Event;
import com.xzlcorp.exception.transfer.service.TransferService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wuxiaoran
 */
@RestController
@Slf4j
@Api(value = "Transfer", tags = {"bug上报"})
@RequestMapping("/report")
public class TransferController {

  @Autowired
  private TransferService service;

  @ApiOperation(value = "上报", notes = "bug上报入口，进行分发处理", httpMethod = "POST")
  @PostMapping()
  public ApiRestResponse report(@RequestBody Event event, HttpServletRequest request) {
    String ipAddress = request.getRemoteAddr();
//    log.info("ipAddress: {}", ipAddress);
//    log.info("report: {}", event);
    service.handleEvent(event, ipAddress);
    return ApiRestResponse.success();
  }
}
