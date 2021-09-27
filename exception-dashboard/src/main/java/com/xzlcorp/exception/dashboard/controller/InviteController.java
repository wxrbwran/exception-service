package com.xzlcorp.exception.dashboard.controller;

import com.xzlcorp.exception.common.common.ApiRestResponse;
import com.xzlcorp.exception.dashboard.model.request.BindUserRequest;
import com.xzlcorp.exception.dashboard.model.request.InviteRequest;
import com.xzlcorp.exception.dashboard.model.vo.InviteVO;
import com.xzlcorp.exception.dashboard.service.InviteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.NoSuchAlgorithmException;

@Api(value = "Invite", tags = {"邀请相关接口"})
@RestController
@RequestMapping("invite")
public class InviteController {

  @Autowired
  private InviteService inviteService;

  /**
   * 生成邀请地址及相关信息
   * @param request
   * @return
   * @throws NoSuchAlgorithmException
   */
  @PostMapping("url")
  @ApiOperation(value = "invite/url", notes = "获取邀请地址", httpMethod = "POST")
  public ApiRestResponse createInviteUrl(@Valid @RequestBody InviteRequest request) throws NoSuchAlgorithmException {
    return inviteService.createInviteUrl(request);
  }

  /**
   * 根据 uuid 获取 invite 信息
   *
   * @param uuid
   */
  @GetMapping()
  public ApiRestResponse getInviteByUUID(@RequestParam String uuid) {
    return ApiRestResponse.success(inviteService.getInviteByUUID(uuid));
  }

  @PostMapping("bind")
  @ApiOperation(value = "invite/bind", notes = "绑定邀请的用户与组织/项目", httpMethod = "POST")
  public ApiRestResponse bindUserWithOrganizationAndProject(@Valid @RequestBody BindUserRequest request) {
    return inviteService.bindUserWithOrganizationAndProject(request);
  }
}
