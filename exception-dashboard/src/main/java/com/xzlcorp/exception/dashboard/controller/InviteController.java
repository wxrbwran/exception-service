package com.xzlcorp.exception.dashboard.controller;

import com.xzlcorp.exception.common.common.ApiRestResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "Invite", tags = {"邀请相关接口"})
@RestController
@RequestMapping("invite")
public class InviteController {

  @PostMapping("url")
  @ApiOperation(value = "invite/url", notes = "获取邀请地址", httpMethod = "POST")
  public ApiRestResponse createInviteUrl() {
    return ApiRestResponse.success();
  }
}
