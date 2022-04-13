package com.xzlcorp.exception.dashboard.controller;

import com.xzlcorp.exception.common.common.ApiRestResponse;
import com.xzlcorp.exception.dashboard.model.pojo.Organization;
import com.xzlcorp.exception.dashboard.model.request.OrganizationRequest;
import com.xzlcorp.exception.dashboard.model.request.UpdateOrganizationRequest;
import com.xzlcorp.exception.dashboard.service.OrganizationService;
import io.swagger.annotations.Api;
import javax.validation.Valid;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

  @ApiOperation(value = "更新机构信息")
  @PutMapping("{orgId}")
  public ApiRestResponse updateOrganizationById(@PathVariable Integer orgId, @RequestBody UpdateOrganizationRequest request) {
    return ApiRestResponse.success(organizationService.updateOrganizationById(orgId, request));
  }

  @ApiOperation(value = "删除机构")
  @DeleteMapping("{orgId}")
  public ApiRestResponse deleteOrganizationById(@PathVariable Integer orgId) {
    organizationService.deleteOrganizationById(orgId);
    return ApiRestResponse.success();
  }
}
