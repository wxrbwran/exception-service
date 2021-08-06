package com.xzlcorp.exception.dashboard.service;

import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

import com.xzlcorp.exception.common.exception.XzlCorpException;
import com.xzlcorp.exception.common.exception.XzlCorpExceptionEnum;
import com.xzlcorp.exception.dashboard.model.dao.OrganizationDynamicSqlSupport;
import com.xzlcorp.exception.dashboard.model.dao.OrganizationMapper;
import com.xzlcorp.exception.dashboard.model.pojo.Organization;
import com.xzlcorp.exception.dashboard.model.pojo.User;
import com.xzlcorp.exception.dashboard.model.request.OrganizationRequest;
import com.xzlcorp.exception.dashboard.model.vo.OrganizationVO;
import com.xzlcorp.exception.dashboard.model.vo.ProjectVO;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
    List<Organization> organizationList = new ArrayList<>();
    orgIds.forEach(orgId -> {
      Organization organization = getOrganizationById(orgId);
      organizationList.add(organization);
    });
    return organizationList;
  }

  public void update(Organization organization) {
    organizationMapper.updateByPrimaryKeySelective(organization);
  }

  public List<OrganizationVO> handleOrganizationToVO(User user) {
    // 设置user的orgs
    List<OrganizationVO> organizationVOList = new ArrayList<>();
    List<Organization> organizations = getOrganizations(Arrays.asList(user.getOrganizations()));
    organizations.forEach(organization -> {
      OrganizationVO organizationVO = new OrganizationVO();
      BeanUtils.copyProperties(organization, organizationVO);
      // 设置org的admin详情
      Integer adminId = organization.getAdmin();
      User adminUser = userService.getUserInfoById(adminId);
      organizationVO.setAdmin(adminUser);
      // 设置org的projects详情
      List<Integer> projects = new ArrayList<>();
      if (organization.getProjects() != null) {
        projects.addAll(Arrays.asList(organization.getProjects()));
        List<ProjectVO> projectVOList = projectService.getProjects(projects);
        organizationVO.setProjects(projectVOList);
      }
      organizationVOList.add(organizationVO);
    });
    return organizationVOList;
  }
}
