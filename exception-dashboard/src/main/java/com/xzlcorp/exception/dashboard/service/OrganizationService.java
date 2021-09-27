package com.xzlcorp.exception.dashboard.service;

import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;
import static org.mybatis.dynamic.sql.SqlBuilder.isIn;

import com.xzlcorp.exception.common.exception.XzlCorpException;
import com.xzlcorp.exception.common.exception.XzlCorpExceptionEnum;
import com.xzlcorp.exception.dashboard.model.dao.OrganizationDynamicSqlSupport;
import com.xzlcorp.exception.dashboard.model.dao.OrganizationMapper;
import com.xzlcorp.exception.dashboard.model.pojo.Organization;
import com.xzlcorp.exception.dashboard.model.pojo.Project;
import com.xzlcorp.exception.dashboard.model.pojo.User;
import com.xzlcorp.exception.dashboard.model.request.OrganizationRequest;
import com.xzlcorp.exception.dashboard.model.request.UpdateOrganizationRequest;
import com.xzlcorp.exception.dashboard.model.vo.OrganizationVO;
import com.xzlcorp.exception.dashboard.model.vo.ProjectVO;

import java.util.*;
import java.util.stream.Collectors;

import com.xzlcorp.exception.dashboard.model.vo.UserVO;
import org.mybatis.dynamic.sql.SqlBuilder;
import org.mybatis.dynamic.sql.render.RenderingStrategies;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author wuxiaoran
 */
@Service
public class OrganizationService {

  @Autowired
  private OrganizationMapper organizationMapper;

  @Autowired
  private UserService userService;

  @Autowired
  private ProjectService projectService;

  @Transactional(propagation = Propagation.REQUIRED)
  public Organization create(OrganizationRequest request) {

    SelectStatementProvider provider = SqlBuilder.select(OrganizationMapper.selectList)
        .from(OrganizationDynamicSqlSupport.organization)
        .where(OrganizationDynamicSqlSupport.name, isEqualTo(request.getName()))
        .build()
        .render(RenderingStrategies.MYBATIS3);
    Organization oldOrganization = organizationMapper.selectOne(provider);
    if (oldOrganization != null) {
      throw new XzlCorpException(XzlCorpExceptionEnum.NAME_EXISTED);
    } else {
      Organization organization = new Organization();
      BeanUtils.copyProperties(request, organization);
      Integer[] users = {request.getAdmin()};
      organization.setUsers(users);
      organizationMapper.insertSelective(organization);

      userService.bindUserWithOrganizationId(organization.getId(), request.getAdmin());
      return organization;
    }
  }


  public Organization getOrganizationById(Integer id) {
    Organization organization = organizationMapper.selectByPrimaryKey(id);
    return organization;
  }

  public List<Organization> getOrganizations(List<Integer> orgIds) {
    List<Organization> organizationList = organizationMapper.select(c ->
        c.where(OrganizationDynamicSqlSupport.id, isIn(orgIds)));
    return organizationList;
  }

  public void update(Organization organization) {
    organizationMapper.updateByPrimaryKeySelective(organization);
  }

  public OrganizationVO handleOrganization2ToVO(Organization organization) {
    OrganizationVO organizationVO = new OrganizationVO();
    BeanUtils.copyProperties(organization, organizationVO);
    return organizationVO;
  }

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



  public void increaseEventCount(String apiKey) {
    Project project = projectService.getProjectByApiKey(apiKey);
    Organization organization = organizationMapper.selectOne(c ->
      c.where(OrganizationDynamicSqlSupport.id, isEqualTo(project.getOrganization()))
    );
    int count = organization.getCount() + 1;
    organization.setCount(count);
    organizationMapper.updateByPrimaryKeySelective(organization);
  }

  public Organization updateOrganizationById(Integer orgId, UpdateOrganizationRequest request) {
    Organization organization = new Organization();
    BeanUtils.copyProperties(request, organization);
    organization.setId(orgId);
    organizationMapper.updateByPrimaryKeySelective(organization);
    return organization;
  }

  public void deleteOrganizationById(Integer orgId) {
    organizationMapper.deleteByPrimaryKey(orgId);
  }

  public void handleOrganizationUsersAndProjectsAndAdmin(List<Organization> organizations, List<OrganizationVO> organizationVOList) {
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
}
