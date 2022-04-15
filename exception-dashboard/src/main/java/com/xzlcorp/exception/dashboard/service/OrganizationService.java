package com.xzlcorp.exception.dashboard.service;

import com.xzlcorp.exception.dashboard.model.pojo.Organization;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xzlcorp.exception.dashboard.model.request.OrganizationRequest;
import com.xzlcorp.exception.dashboard.model.request.UpdateOrganizationRequest;
import com.xzlcorp.exception.dashboard.model.vo.OrganizationVO;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
* @author wuxiaoran
* @description 针对表【t_organization(组织表)】的数据库操作Service
* @createDate 2022-04-13 11:38:57
*/
public interface OrganizationService extends IService<Organization> {

  @Transactional(propagation = Propagation.REQUIRED)
  Organization create(OrganizationRequest request);

  void update(Organization organization);

  OrganizationVO handleOrganization2ToVO(Organization organization);

  List<OrganizationVO> handleOrganization2ToVOList(
      List<Organization> organizations
  );

  void increaseEventCount(String apiKey);

  Organization updateOrganizationById(Integer orgId, UpdateOrganizationRequest request);

  void handleOrganizationUsersAndProjectsAndAdmin(
      List<Organization> organizations, List<OrganizationVO> organizationVOList);

  void addUser(Integer organizationId, List<Integer> userIds);

}
