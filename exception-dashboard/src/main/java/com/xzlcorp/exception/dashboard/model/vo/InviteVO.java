package com.xzlcorp.exception.dashboard.model.vo;

import lombok.Data;

import java.util.List;

@Data
public class InviteVO {
  private String uuid;

  private String auth;

  private UserVO inviter;

  private OrganizationVO organization;

  private List<ProjectVO> projects;
}
