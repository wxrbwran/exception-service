package com.xzlcorp.exception.dashboard.model.request;

import com.xzlcorp.exception.dashboard.enums.NotificationRuleLevel;
import com.xzlcorp.exception.dashboard.model.pojo.notification.NotificationRuleData;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@ApiModel(value="AddNotificationRequest",description="添加通知规则")
public class EditNotificationRuleRequest {

//  @NotNull(message = "ruleId不能为空")
//  @ApiModelProperty(value = "ruleId", notes = "规则id", required = true)
//  private Integer ruleId;

  @ApiModelProperty(value = "name", notes = "规则名称", required = true)
  private String name;

  @ApiModelProperty(value = "data", notes = "规则内容", required = true)
  private NotificationRuleData data;

  @ApiModelProperty(value = "level", notes = "规则级别", required = true)
  private String level;

  @ApiModelProperty(value = "interval", notes = "静默期", required = true)
  private Long interval;

  @ApiModelProperty(value = "open", notes = "通知开关", required = true)
  private Boolean open;
}
