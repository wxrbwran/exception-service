package com.xzlcorp.exception.dashboard.model.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;

/**
 * @author wuxiaoran
 */
@Data
public class OrganizationRequest {
  @NotNull(message="机构名称不能为空")
  @Size(min=2, max=20, message="机构名称长度为2-20")
  private String name;

  private String introduction;

  private Integer admin;

}
