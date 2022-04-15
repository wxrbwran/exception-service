package com.xzlcorp.exception.dashboard.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xzlcorp.exception.common.exception.XzlCorpException;
import com.xzlcorp.exception.common.exception.XzlCorpExceptionEnum;
import com.xzlcorp.exception.common.utils.UniqueList;
import com.xzlcorp.exception.dashboard.mapper.OrganizationMapper;
import com.xzlcorp.exception.dashboard.model.pojo.Organization;
import com.xzlcorp.exception.dashboard.model.pojo.Project;
import com.xzlcorp.exception.dashboard.model.pojo.User;
import com.xzlcorp.exception.dashboard.model.request.OrganizationRequest;
import com.xzlcorp.exception.dashboard.model.request.UpdateOrganizationRequest;
import com.xzlcorp.exception.dashboard.model.vo.OrganizationVO;
import com.xzlcorp.exception.dashboard.model.vo.UserVO;
import com.xzlcorp.exception.dashboard.service.OrganizationService;
import com.xzlcorp.exception.dashboard.service.ProjectService;
import com.xzlcorp.exception.dashboard.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
* @author wuxiaoran
* @description 针对表【t_organization(组织表)】的数据库操作Service实现
* @createDate 2022-04-13 11:38:57
*/
@Service
public class OrganizationServiceImpl extends ServiceImpl<OrganizationMapper, Organization>
    implements OrganizationService{
  @Autowired
  private UserService userService;

  @Autowired
  private ProjectService projectService;

  @Override
  @Transactional(propagation = Propagation.REQUIRED)
  public Organization create(OrganizationRequest request) {
    LambdaQueryWrapper<Organization> queryWrapper = new LambdaQueryWrapper<>();
    queryWrapper.eq(Organization::getName, request.getName());
    Organization oldOrganization = this.getOne(queryWrapper);
    if (oldOrganization != null) {
      throw new XzlCorpException(XzlCorpExceptionEnum.NAME_EXISTED);
    } else {
      Organization organization = new Organization();
      BeanUtils.copyProperties(request, organization);
      Integer[] users = {request.getAdmin()};
      organization.setUsers(users);
      this.save(organization);
//      organizationMapper.insertSelective(organization);
      userService.bindUserWithOrganizationId(organization.getId(), request.getAdmin());
      return organization;
    }
  }

  @Override
  public void update(Organization organization) {
    this.updateById(organization);
  }
  @Override
  public OrganizationVO handleOrganization2ToVO(Organization organization) {
    OrganizationVO organizationVO = new OrganizationVO();
    BeanUtils.copyProperties(organization, organizationVO);
    return organizationVO;
  }
  @Override
  public List<OrganizationVO> handleOrganization2ToVOList(
      List<Organization> organizations
  ) {
    // 设置user的orgs
    List<OrganizationVO> organizationVOList = new ArrayList<>();
    organizations.forEach(organization -> {
      OrganizationVO organizationVO = new OrganizationVO();
      BeanUtils.copyProperties(organization, organizationVO);
      organizationVOList.add(organizationVO);
    });
    return organizationVOList;
  }


  @Override
  public void increaseEventCount(String apiKey) {
    Project project = projectService.getProjectByApiKey(apiKey);
    Organization organization = this.getById(project.getOrganization());
    int count = organization.getCount() + 1;
    organization.setCount(count);
    this.updateById(organization);
  }

  @Override
  public Organization updateOrganizationById(Integer orgId, UpdateOrganizationRequest request) {
    Organization organization = new Organization();
    BeanUtils.copyProperties(request, organization);
    organization.setId(orgId);
    this.updateById(organization);
//    organizationMapper.updateByPrimaryKeySelective(organization);
    return organization;
  }

  @Override
  public void handleOrganizationUsersAndProjectsAndAdmin(
      List<Organization> organizations, List<OrganizationVO> organizationVOList) {
//    Map<Integer, UserVO> userMap = new HashMap<>();
    organizations.forEach(organization -> {
      Integer orgId = organization.getId();
      OrganizationVO organizationVO = organizationVOList
          .stream()
          .filter(orgVO -> orgVO.getId().equals(orgId))
          .collect(Collectors.toList())
          .get(0);
      // 添加admin
      Integer adminId = organization.getAdmin();
      UserVO adminVO = new UserVO();
//      if (userMap.get(adminId) != null) {
//        adminVO = userMap.get(adminId);
//      } else {
      adminVO = userService.getUserInfoByIdSimple(adminId);
//        userMap.put(adminId, adminVO);
//      }
      organizationVO.setAdmin(adminVO);
      // 添加admin end

      // 添加users
      Integer[] userIds = organization.getUsers();
      List<User> users = userService.getUserInfoByIds(userIds);
      organizationVO.setUsers(userService.handleUsers2VOList(users));
      // 添加users end

      // 添加PROJECTS
      List<Integer> projectIds = Arrays.asList(organization.getProjects());
      List<Project> projects = projectService.getProjects(projectIds);
      organizationVO.setProjects(projectService.handleProjects2VOList(projects));
      // 添加PROJECTS end

    });

//    organizationVO.setAdmin(adminUser);
//    // 设置org的projects详情
//    List<Integer> projects = new ArrayList<>();
//    if (organization.getProjects() != null) {
//      projects.addAll(Arrays.asList(organization.getProjects()));
//      List<ProjectVO> projectVOList = projectService
//          .handleProjects2VOList(projectService.getProjects(projects));
//      organizationVO.setProjects(projectVOList);
//    }
  }
  @Override
  public void addUser(Integer organizationId, List<Integer> userIds) {
    Organization organization = this.getById(organizationId);
    List<Integer> users = Arrays.stream(organization.getUsers()).collect(Collectors.toList());
    List<Integer> userIdSet = UniqueList.toUnique(users, userIds);
    organization.setUsers(userIdSet.toArray(new Integer[userIdSet.size()]));
    this.updateById(organization);
  }
}




