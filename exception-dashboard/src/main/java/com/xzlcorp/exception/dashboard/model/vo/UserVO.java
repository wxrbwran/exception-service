package com.xzlcorp.exception.dashboard.model.vo;

import java.util.List;
import lombok.Data;

/**
 * @author wuxiaoran
 */
@Data
public class UserVO {
  private Integer id;

  private String name;

  private String email;

  private String mobile;

  private String avatar;

  private Boolean activated;

  private List<Integer> organizationIds;
  private List<OrganizationVO> organizations;

  private List<Integer> projectIds;
  private List<ProjectVO> projects;


}
