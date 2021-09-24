package com.xzlcorp.exception.dashboard.controller;

import com.xzlcorp.exception.common.common.ApiRestResponse;
import com.xzlcorp.exception.dashboard.model.pojo.Organization;
import com.xzlcorp.exception.dashboard.model.pojo.Project;
import com.xzlcorp.exception.dashboard.model.pojo.User;
import com.xzlcorp.exception.dashboard.model.request.UpdateUserRequest;
import com.xzlcorp.exception.dashboard.model.vo.OrganizationVO;
import com.xzlcorp.exception.dashboard.model.vo.ProjectVO;
import com.xzlcorp.exception.dashboard.model.vo.UserVO;
import com.xzlcorp.exception.dashboard.service.OrganizationService;
import com.xzlcorp.exception.dashboard.service.ProjectService;
import com.xzlcorp.exception.dashboard.service.UserService;
import io.swagger.annotations.Api;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author wuxiaoran
 */

@RestController
@RequestMapping("/users")
@Api(value = "User", tags = {"用户信息相关接口"})
public class UserController {

  @Autowired
  private UserService userService;

  @Autowired
  private OrganizationService organizationService;

  @Autowired
  private ProjectService projectService;

  @GetMapping("{userId}")
  public ApiRestResponse getUserInfo(@PathVariable Integer userId) {
    User user = userService.getUserInfoById(userId);
    UserVO userVO = new UserVO();
    BeanUtils.copyProperties(user, userVO);
    // 设置user的orgs

    List<OrganizationVO> organizationVOList = organizationService.handleOrganizationToVO(user);

    // 设置user的projects
    List<ProjectVO> projects = projectService
        .getProjects(Arrays.asList(user.getProjects()));

    userVO.setOrganizations(organizationVOList);
    userVO.setProjects(projects);

    return ApiRestResponse.success(userVO);
  }

  @PatchMapping("{userId}")
  public ApiRestResponse<User> updateUserInfo(@PathVariable Integer userId, @RequestBody UpdateUserRequest request) {
    User user = userService.updateUserById(userId, request);
    return ApiRestResponse.success(user);
  }

}
