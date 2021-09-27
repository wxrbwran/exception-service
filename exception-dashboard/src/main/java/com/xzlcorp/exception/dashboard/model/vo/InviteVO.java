package com.xzlcorp.exception.dashboard.model.vo;

import lombok.Data;

@Data
public class InviteVO {
  private String uuid;

  private String auth;

  private UserVO inviter;

  private OrganizationVO organization;
}
