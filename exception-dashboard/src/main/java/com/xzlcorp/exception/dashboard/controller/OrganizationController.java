package com.xzlcorp.exception.dashboard.controller;

import com.xzlcorp.exception.common.common.ApiRestResponse;
import com.xzlcorp.exception.dashboard.model.pojo.Organization;
import com.xzlcorp.exception.dashboard.model.request.OrganizationRequest;
import com.xzlcorp.exception.dashboard.model.vo.OrganizationVO;
import com.xzlcorp.exception.dashboard.model.vo.UserVO;
import com.xzlcorp.exception.dashboard.service.OrganizationService;
import com.xzlcorp.exception.dashboard.service.UserService;
import io.swagger.annotations.Api;
import javax.validation.Valid;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wuxiaoran
 */

@RestController
@RequestMapping("/organizations")
@Api(value = "Organizations", tags = {"组织机构相关接口"})
public class OrganizationController {

  @Autowired
  private OrganizationService organizationService;

  @ApiOperation(value = "创建orgnization")
  @PostMapping()
  public ApiRestResponse create(@Valid @RequestBody OrganizationRequest request) {
    Organization organization = organizationService.create(request);
    return ApiRestResponse.success(organization);
  }

  @ApiOperation(value = "event自增1")
  @PostMapping("event/increase")
  public void increaseEventCount(String apiKey) {
    organizationService.increaseEventCount(apiKey);
  }

}
