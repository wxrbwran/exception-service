package com.xzlcorp.exception.dashboard.controller;

import com.xzlcorp.exception.common.common.ApiRestResponse;
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
import io.swagger.annotations.Api;

import java.security.NoSuchAlgorithmException;
import java.util.*;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author wuxiaoran
 */
@Slf4j
@RestController
//@RequestMapping("/users")
@Api(value = "User", tags = {"用户信息相关接口"})
public class UserController {

  @Autowired
  private UserService userService;

  @Autowired
  private OrganizationService organizationService;

  @Autowired
  private ProjectService projectService;

  @GetMapping("/users/{userId}")
  public ApiRestResponse getUserInfo(@PathVariable Integer userId) {
    User user = userService.getUserInfoById(userId);
    UserVO userVO = userService.handleUser2VO(user);

    // 设置user的机构信息 organization
    List<Integer> orgIds = userVO.getOrganizationIds();
    List<OrganizationVO> organizationVOList = new ArrayList<>();
    if (orgIds != null && orgIds.size() > 0) {
      List<Organization> organizations = organizationService.getOrganizations(orgIds);
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

    return ApiRestResponse.success(userVO);
  }

  @PatchMapping("/users/{userId}")
  public ApiRestResponse<User> updateUserInfo(@PathVariable Integer userId, @RequestBody UpdateUserRequest request) {
    User user = userService.updateUserById(userId, request);
    return ApiRestResponse.success(user);
  }


  @PostMapping("/auth/signup")
  public ApiRestResponse signup(@Valid @RequestBody SignupRequest request)
      throws NoSuchAlgorithmException {
    UserVO userVO = userService.signup(request);
    return ApiRestResponse.success(userVO);
  }

  @PostMapping("/auth/login")
  public ApiRestResponse login(@Valid @RequestBody LoginRequest request)
      throws NoSuchAlgorithmException {
    UserVO userVO = userService.login(request);
    return ApiRestResponse.success(userVO);
  }
}
