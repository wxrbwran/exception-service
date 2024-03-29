package com.xzlcorp.exception.dashboard.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xzlcorp.exception.common.exception.XzlCorpException;
import com.xzlcorp.exception.common.exception.XzlCorpExceptionEnum;
import com.xzlcorp.exception.common.utils.Md5Utils;
import com.xzlcorp.exception.common.utils.RedisConstants;
import com.xzlcorp.exception.dashboard.mapper.UserMapper;
import com.xzlcorp.exception.dashboard.model.pojo.Organization;
import com.xzlcorp.exception.dashboard.model.pojo.Project;
import com.xzlcorp.exception.dashboard.model.pojo.User;
import com.xzlcorp.exception.dashboard.model.request.LoginRequest;
import com.xzlcorp.exception.dashboard.model.request.SignupRequest;
import com.xzlcorp.exception.dashboard.model.request.UpdateUserRequest;
import com.xzlcorp.exception.dashboard.model.vo.OrganizationVO;
import com.xzlcorp.exception.dashboard.model.vo.ProjectVO;
import com.xzlcorp.exception.dashboard.model.vo.UserVO;
import com.xzlcorp.exception.dashboard.service.OrganizationService;
import com.xzlcorp.exception.dashboard.service.ProjectService;
import com.xzlcorp.exception.dashboard.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
* @author wuxiaoran
* @description 针对表【t_user】的数据库操作Service实现
* @createDate 2022-04-13 11:34:48
*/
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService {

  @Autowired
  private StringRedisTemplate stringRedisTemplate;

  @Autowired
  private OrganizationService organizationService;

  @Autowired
  private ProjectService projectService;

  @Override
  public UserVO signup(SignupRequest request) throws NoSuchAlgorithmException {
    LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
    queryWrapper.eq(User::getEmail, request.getEmail());
    User oldUser = this.getOne(queryWrapper);
    if (oldUser != null) {
      throw new XzlCorpException(XzlCorpExceptionEnum.EMAIL_EXISTED);
    } else {
      String md5Password = Md5Utils.getMD5String(request.getPassword());
      User user = new User();
      BeanUtils.copyProperties(request, user);
      user.setPassword(md5Password);
      user.setActivated(true);
      this.save(user);
      UserVO userVO = new UserVO();
      BeanUtils.copyProperties(user, userVO);
      return userVO;
    }
  }
  @Override
  public UserVO login(LoginRequest request) throws NoSuchAlgorithmException {
    LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
    queryWrapper.eq(User::getEmail, request.getEmail());
    queryWrapper.eq(User::getPassword, Md5Utils.getMD5String(request.getPassword()));
    User oldUser = this.getOne(queryWrapper);
    if (oldUser == null) {
      throw new XzlCorpException(XzlCorpExceptionEnum.INVALID_EMAIL_PASSWORD);
    }
    UserVO userVO = new UserVO();
    BeanUtils.copyProperties(oldUser, userVO);
    return userVO;
  }

  @Override
  public User getUserInfoById(Integer id) {
    User user = this.getById(id);
    return user;
  }

  @Override
  public List<User> getUserInfoByIds(Integer[] userIds) {
    List<User> users = this.listByIds(List.of(userIds));
    return users;
  }

  @Override
  public void deleteRedisUserInfo(Integer userId) {
    stringRedisTemplate.delete(RedisConstants.USER_INFO_KEY + userId);
  }

  @Override
  public UserVO getUserInfoByIdSimple(Integer id) {
    User user = this.getUserInfoById(id);
    UserVO userVO = new UserVO();
    BeanUtils.copyProperties(user, userVO);
    return userVO;
  }
  @Override
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
  @Override
  public List<UserVO> handleUsers2VOList(List<User> users) {
    List<UserVO> userVOList = new ArrayList<>();
    users.forEach(user -> {
      UserVO userVO = handleUser2VO(user);
      userVOList.add(userVO);
    });
    return userVOList;
  }

  @Override
  public User updateUser(User user) {
    this.saveOrUpdate(user);
    return user;
  }

  @Override
  public User updateUserById(Integer userId, UpdateUserRequest request) {
    User user = new User();
    BeanUtils.copyProperties(request, user);
    user.setId(userId);
    user = updateUser(user);
    return user;
  }

  @Override
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
    updateUser(user);
  }

  @Override
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
    updateUser(user);
  }

  @Override
  public UserVO getFullUserInfo(Integer userId) {
    User user = getUserInfoById(userId);
    UserVO userVO = handleUser2VO(user);

    // 设置user的机构信息 organization
    List<Integer> orgIds = userVO.getOrganizationIds();
    List<OrganizationVO> organizationVOList = new ArrayList<>();
    if (orgIds != null && orgIds.size() > 0) {
      List<Organization> organizations = organizationService.listByIds(orgIds);
      log.info("organizations, {}", organizations);
      organizationVOList = organizationService.handleOrganization2ToVOList(organizations);
      log.info("organizationVOList, {}", organizationVOList);
      // 处理机构的人员信息，项目信息，管理员信息。
      organizationService.handleOrganizationUsersAndProjectsAndAdmin(organizations, organizationVOList);
    }
    userVO.setOrganizations(organizationVOList);

    // 设置user的项目信息 projects
    List<Integer> projectIds = userVO.getProjectIds();
    List<ProjectVO> projectVOList = new ArrayList<>();
    if (projectIds != null && projectIds.size() > 0) {
      List<Project> projects = projectService.getProjects(projectIds);
      log.info("projects, {}", projects);
      projectVOList = projectService.handleProjects2VOList(projects);
      log.info("projectVOList, {}", projectVOList);
    }

    userVO.setProjects(projectVOList);
    return userVO;
  }
}




