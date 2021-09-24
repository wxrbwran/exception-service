package com.xzlcorp.exception.dashboard.service;

import com.xzlcorp.exception.dashboard.model.dao.UserMapper;
import com.xzlcorp.exception.dashboard.model.pojo.User;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.xzlcorp.exception.dashboard.model.request.UpdateUserRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author wuxiaoran
 */
@Service
@Slf4j
public class UserService {

  @Autowired
  private UserMapper userMapper;

  public User getUserInfoById(Integer id) {
    User user = userMapper.selectByPrimaryKey(id);
    return user;
  }

  public User update(User user) {
    userMapper.updateByPrimaryKeySelective(user);
    return user;
  }

  public User updateUserById(Integer userId, UpdateUserRequest request) {
    User user = new User();
    BeanUtils.copyProperties(request, user);
    user.setId(userId);
    user = update(user);
    return user;
  }

  public void bindUserWithProjectId(Integer projectId, Integer userId) {
    User user = getUserInfoById(userId);
    Integer[] projects = user.getProjects();
    List<Integer> projectList = new ArrayList<>();
    if (projects != null) {
      projectList.addAll(Arrays.asList(projects));
    }
    if (!projectList.contains(projectId)) {
      projectList.add(projectId);
    }
    Integer[] newProjects = projectList.toArray(new Integer[projectList.size()]);
    user.setProjects(newProjects);
    update(user);
  }

  public void bindUserWithOrganizationId(Integer orgId, Integer userId) {
    User user = getUserInfoById(userId);
    Integer[] organizations = user.getOrganizations();
    log.info("bindUserWithOrganizationId:organizations => {}", organizations);
    List<Integer> organizationList = new ArrayList<>();
    if (organizations != null) {
      organizationList.addAll(Arrays.asList(organizations));
//      organizationList = Arrays.asList(organizations);
      log.info("bindUserWithOrganizationId:organizationList => {}", organizationList);
    }
    if (!organizationList.contains(orgId)) {
      log.info("contains true");
      organizationList.add(orgId);
    }
    Integer[] newOrganizations = organizationList.toArray(new Integer[organizationList.size()]);
    user.setOrganizations(newOrganizations);
    update(user);
  }
}
