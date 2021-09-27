package com.xzlcorp.exception.dashboard.model.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@ApiModel(value="BindUserRequest",description="绑定受邀人")
public class BindUserRequest {

  @NotNull(message="uuid不能为空")
  @ApiModelProperty(value = "uuid", notes = "UUID", required = true)
  private String uuid;

  @NotNull(message="userId不能为空")
  @ApiModelProperty(value = "userId", notes = "受邀人Id", required = true)
  private Integer userId;


}
