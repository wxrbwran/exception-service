package com.xzlcorp.exception.dashboard.model.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@ApiModel(value="Project",description="创建项目")
public class InviteRequest {
  @NotNull(message = "auth不能为null")
  @ApiModelProperty(value = "auth", notes = "受邀人权限，目前都为default", required = true)
  private String auth;

  @NotNull(message = "projects不能为null")
  @ApiModelProperty(value = "projects", notes = "受邀人项目", required = true)
  private List<Integer> projects;

  @NotNull(message = "organizationId不能为null")
  @ApiModelProperty(value = "organizationId", notes = "受邀人组织", required = true)
  private Integer organizationId;

  @NotNull(message = "inviterId不能为null")
  @ApiModelProperty(value = "inviterId", notes = "邀请人ID", required = true)
  private Integer inviterId;

}
