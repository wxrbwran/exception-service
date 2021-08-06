package com.xzlcorp.exception.dashboard.model.vo;

import com.xzlcorp.exception.dashboard.model.pojo.Project;
import com.xzlcorp.exception.dashboard.model.pojo.User;
import java.util.List;
import lombok.Data;

/**
 * @author wuxiaoran
 */
@Data
public class OrganizationVO {

  private Integer id;

  private String name;

  private String introduction;

  private User admin;

  private List<ProjectVO> projects;

  private Integer count;

  private Integer[] users;
}
