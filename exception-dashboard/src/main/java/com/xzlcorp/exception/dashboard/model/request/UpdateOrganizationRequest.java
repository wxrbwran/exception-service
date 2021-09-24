package com.xzlcorp.exception.dashboard.model.request;

import lombok.Data;

@Data
public class UpdateOrganizationRequest {
  private String name;

  private String introduction;

  private String avatar;
}
