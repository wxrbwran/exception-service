package com.xzlcorp.exception.dashboard.model.request;

import lombok.Data;

@Data
public class UpdateUserRequest {
  private String name;

  private String email;

  private String avatar;

}
