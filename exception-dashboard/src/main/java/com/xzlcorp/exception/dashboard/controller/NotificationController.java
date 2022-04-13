package com.xzlcorp.exception.dashboard.controller;

import com.xzlcorp.exception.common.common.ApiRestResponse;
import com.xzlcorp.exception.dashboard.model.pojo.NotificationRule;
import com.xzlcorp.exception.dashboard.model.pojo.NotificationSetting;
import com.xzlcorp.exception.dashboard.model.request.AddNotificationRuleRequest;
import com.xzlcorp.exception.dashboard.model.request.EditNotificationRuleRequest;
import com.xzlcorp.exception.dashboard.service.impl.NotificationRuleServiceImpl;
import com.xzlcorp.exception.dashboard.service.impl.NotificationSettingServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(value = "Notification", tags = {"相关接口"})
@RestController
@RequestMapping("notification")
public class NotificationController {

  @Autowired
  private NotificationSettingServiceImpl notificationSettingService;

  @Autowired
  private NotificationRuleServiceImpl notificationRuleService;

  @PostMapping("rules")
  @ApiOperation(value = "notification/rules", notes = "创建通知规则", httpMethod = "POST")
  public ApiRestResponse createNotificationRule(@Valid @RequestBody AddNotificationRuleRequest request) {

    return notificationRuleService.createNotificationRule(request);
  }

  @GetMapping("rules")
  @ApiOperation(value = "notification/rules", notes = "获取通知规则列表", httpMethod = "GET")
  public ApiRestResponse getNotificationRules(@RequestParam Integer projectId) {
    List<NotificationRule> notificationRuleList = notificationRuleService.getNotificationRules(projectId);
    return ApiRestResponse.success(notificationRuleList);
  }

  @PatchMapping("rules/{ruleId}")
  @ApiOperation(value = "notification/rules/{ruleId}", notes = "更新通知规则", httpMethod = "PATCH")
  public ApiRestResponse updateNotificationRule(@PathVariable("ruleId") Integer ruleId, @RequestBody EditNotificationRuleRequest request) {
    return notificationRuleService.updateNotificationRule(ruleId, request);
  }

  @DeleteMapping("rules/{ruleId}")
  @ApiOperation(value = "notification/rules/{ruleId}", notes = "删除通知规则", httpMethod = "DELETE")
  public ApiRestResponse deleteNotificationRule(@PathVariable("ruleId") Integer ruleId) {
    return notificationRuleService.deleteNotificationRule(ruleId);
  }

  @GetMapping("setting")
  @ApiOperation(value = "notification/setting", notes = "获取通知设置", httpMethod = "GET")
  public ApiRestResponse getNotificationSetting(@RequestParam Integer projectId) {
    NotificationSetting setting = notificationSettingService.getNotificationSetting(projectId);
    return ApiRestResponse.success(setting);
  }

}
