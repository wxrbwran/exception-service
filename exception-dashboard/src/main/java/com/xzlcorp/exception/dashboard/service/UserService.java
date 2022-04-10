package com.xzlcorp.exception.dashboard.service;

import com.xzlcorp.exception.dashboard.model.pojo.User;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.xzlcorp.exception.dashboard.model.request.UpdateUserRequest;
import com.xzlcorp.exception.dashboard.model.vo.UserVO;
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

//  @Autowired
//  private UserMapper userMapper;

  public User getUserInfoById(Integer id) {
//    User user = userMapper.selectByPrimaryKey(id);
//    return user;
    return null;
  }

  public List<User> getUserInfoByIds(Integer[] userIds) {
//    List<User> users = userMapper.select(c ->
//        c.where(UserDynamicSqlSupport.id, isIn(userIds)));
//    return users;
    return null;

  }

  public UserVO getUserInfoByIdSimple(Integer id) {
//    User user = userMapper.selectByPrimaryKey(id);
//    UserVO userVO = new UserVO();
//    BeanUtils.copyProperties(user, userVO);
//    return userVO;
    return null;

  }

  public UserVO handleUser2VO(User user) {
    UserVO userVO = new UserVO();
    BeanUtils.copyProperties(user, userVO);
    if (user.getProjects() != null) {
      userVO.setProjectIds(Arrays.stream(user.getProjects()).collect(Collectors.toList()));
    }
    if (user.getOrganizations() != null) {
      userVO.setOrganizationIds(Arrays.stream(user.getOrganizations()).collect(Collectors.toList()));
    }
    return userVO;
  }

  public List<UserVO> handleUsers2VOList(List<User> users) {
    List<UserVO> userVOList = new ArrayList<>();
    users.forEach(user -> {
      UserVO userVO = handleUser2VO(user);
      userVOList.add(userVO);
    });
    return userVOList;
  }

  public User update(User user) {
//    userMapper.updateByPrimaryKeySelective(user);
//    return user;
    return null;

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
