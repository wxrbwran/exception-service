package com.xzlcorp.exception.dashboard.model.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;

/**
 * @author wuxiaoran
 */
@Data
public class SignupRequest {

  @NotNull(message="用户名不能为空")
  @Size(min=2, max=15, message="用户名长度2-15")
  private String name;

  @NotNull(message="邮箱不能为空")
  @Email(message="请输入正确的邮箱格式")
  private String email;

  @NotNull(message="密码不能为空")
  @Size(min=3, max=30, message="密码长度3-30")
  private String password;

}
