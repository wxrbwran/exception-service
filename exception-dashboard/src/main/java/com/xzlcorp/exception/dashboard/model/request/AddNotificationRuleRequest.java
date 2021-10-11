package com.xzlcorp.exception.dashboard.model.request;

import com.xzlcorp.exception.dashboard.enums.NotificationRuleLevel;
import com.xzlcorp.exception.dashboard.model.pojo.notification.NotificationRuleData;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@ApiModel(value="AddNotificationRequest",description="添加通知规则")
public class AddNotificationRuleRequest {

  @NotNull(message="projectId不能为空")
  @ApiModelProperty(value = "projectId", notes = "项目id", required = true)
  private Integer projectId;

  @NotNull(message="规则名称不能为空")
  @ApiModelProperty(value = "name", notes = "规则名称", required = true)
  private String name;

  @NotNull(message="规则内容不能为空")
  @ApiModelProperty(value = "data", notes = "规则内容", required = true)
  private NotificationRuleData data;

  @NotNull(message="规则等级不能为空")
  @ApiModelProperty(value = "level", notes = "规则级别", required = true)
  private String level;

  @NotNull(message="通知静默期不能为空")
  @ApiModelProperty(value = "interval", notes = "静默期", required = true)
  private Long interval;

  @NotNull(message="通知开关不能为空")
  @ApiModelProperty(value = "open", notes = "通知开关", required = true)
  private Boolean open;
}
