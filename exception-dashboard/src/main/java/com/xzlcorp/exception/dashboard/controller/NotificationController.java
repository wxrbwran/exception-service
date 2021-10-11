package com.xzlcorp.exception.dashboard.controller;

import com.xzlcorp.exception.common.common.ApiRestResponse;
import com.xzlcorp.exception.dashboard.model.pojo.notification.NotificationRule;
import com.xzlcorp.exception.dashboard.model.request.AddNotificationRuleRequest;
import com.xzlcorp.exception.dashboard.model.request.EditNotificationRuleRequest;
import com.xzlcorp.exception.dashboard.service.NotificationService;
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
  private NotificationService notificationService;

  @PostMapping("rules")
  @ApiOperation(value = "notification/rules", notes = "创建通知规则", httpMethod = "POST")
  public ApiRestResponse createNotificationRule(@Valid @RequestBody AddNotificationRuleRequest request) {

    return notificationService.createNotificationRule(request);
  }

  @GetMapping("rules")
  @ApiOperation(value = "notification/rules", notes = "获取通知规则列表", httpMethod = "GET")
  public ApiRestResponse getNotificationRules(@RequestParam Integer projectId) {
    List<NotificationRule> notificationRuleList = notificationService.getNotificationRules(projectId);
    return ApiRestResponse.success(notificationRuleList);
  }

  @PatchMapping("rules/{ruleId}")
  @ApiOperation(value = "notification/rules/{ruleId}", notes = "更新通知规则", httpMethod = "PATCH")
  public ApiRestResponse updateNotificationRule(@PathVariable("ruleId") Integer ruleId, @RequestBody EditNotificationRuleRequest request) {
    return notificationService.updateNotificationRule(ruleId, request);
  }

  @DeleteMapping("rules/{ruleId}")
  @ApiOperation(value = "notification/rules/{ruleId}", notes = "删除通知规则", httpMethod = "DELETE")
  public ApiRestResponse deleteNotificationRule(@PathVariable("ruleId") Integer ruleId) {
    return notificationService.deleteNotificationRule(ruleId);
  }
}
