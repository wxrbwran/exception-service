package com.xzlcorp.exception.dashboard.model.request;

import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 * @author wuxiaoran
 */
@Data
public class LoginRequest {

  @NotNull(message="邮箱不能为空")
  private String email;

  @NotNull(message="密码不能为空")
  private String password;

}
