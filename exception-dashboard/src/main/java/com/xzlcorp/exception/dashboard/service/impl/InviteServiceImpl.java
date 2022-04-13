package com.xzlcorp.exception.dashboard.service.impl;

import cn.hutool.core.lang.generator.UUIDGenerator;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xzlcorp.exception.common.common.ApiRestResponse;
import com.xzlcorp.exception.common.common.Constant;
import com.xzlcorp.exception.common.exception.XzlCorpException;
import com.xzlcorp.exception.common.exception.XzlCorpExceptionEnum;
import com.xzlcorp.exception.common.utils.ArrayToList;
import com.xzlcorp.exception.common.utils.Md5Utils;
import com.xzlcorp.exception.common.utils.UniqueList;
import com.xzlcorp.exception.dashboard.model.pojo.Invite;
import com.xzlcorp.exception.dashboard.model.pojo.Organization;
import com.xzlcorp.exception.dashboard.model.pojo.Project;
import com.xzlcorp.exception.dashboard.model.pojo.User;
import com.xzlcorp.exception.dashboard.model.request.BindUserRequest;
import com.xzlcorp.exception.dashboard.model.request.InviteRequest;
import com.xzlcorp.exception.dashboard.model.vo.InviteVO;
import com.xzlcorp.exception.dashboard.service.InviteService;
import com.xzlcorp.exception.dashboard.mapper.InviteMapper;
import com.xzlcorp.exception.dashboard.service.OrganizationService;
import com.xzlcorp.exception.dashboard.service.ProjectService;
import com.xzlcorp.exception.dashboard.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
* @author wuxiaoran
* @description 针对表【t_invite(邀请表)】的数据库操作Service实现
* @createDate 2022-04-13 11:32:48
*/
@Service
@Slf4j
public class InviteServiceImpl extends ServiceImpl<InviteMapper, Invite>
    implements InviteService{

  @Autowired
  private UserService userService;

  @Autowired
  private OrganizationService organizationService;

  @Autowired
  private ProjectService projectService;

  @Override
  public ApiRestResponse createInviteUrl(InviteRequest request) throws NoSuchAlgorithmException {
    String auth = request.getAuth();
    List<Integer> projects = request.getProjects();
    Integer organizationId = request.getOrganizationId();
    Integer inviterId = request.getInviterId();

    String hash = Md5Utils.getMD5String(auth + "," + projects.toString() + "," + organizationId + "," + inviterId);
    log.info("createInviteUrl hash: {}", hash);
    LambdaQueryWrapper<Invite> queryWrapper = new LambdaQueryWrapper<>();
    queryWrapper.eq(Invite::getHash, hash);
    Invite oldInvite = this.getOne(queryWrapper);
    if (oldInvite != null) {
      return ApiRestResponse.success(oldInvite.getUrl());
    }
    String uuid = new UUIDGenerator().next();
    log.info("createInviteUrl uuid: {}", uuid);
    String url = "/invite?id=" + uuid;
    Invite invite = new Invite();
    BeanUtils.copyProperties(request, invite);
    invite.setUuid(uuid);
    invite.setHash(hash);
    invite.setUrl(url);
    invite.setExpires(new Date(System.currentTimeMillis() + Constant.ONE_DAY_MILLS_SECOND * 14));
    invite.setOrganization(organizationId);
    invite.setProjects(projects.toArray(new Integer[projects.size()]));

    invite.setInviter(inviterId);
//    this.insertSelective(invite);
    this.saveOrUpdate(invite);
    return ApiRestResponse.success(invite.getUrl());
  }

  @Override
  public InviteVO getInviteByUUID(String uuid) {
    LambdaQueryWrapper<Invite> queryWrapper = new LambdaQueryWrapper<>();
    queryWrapper.eq(Invite::getUuid, uuid);
    Invite invite = this.getOne(queryWrapper);
    if (invite != null) {
      InviteVO inviteVO = new InviteVO();
      BeanUtils.copyProperties(invite, inviteVO);
      User inviter = userService.getUserInfoById(invite.getInviter());
      Organization organization = organizationService.getOrganizationById(invite.getOrganization());
      List<Project> projects = projectService.getProjects(Arrays.asList(invite.getProjects()));
      inviteVO.setInviter(userService.handleUser2VO(inviter));
      inviteVO.setOrganization(organizationService.handleOrganization2ToVO(organization));
      inviteVO.setProjects(projectService.handleProjects2VOList(projects));
      return inviteVO;
    } else {
      throw new XzlCorpException(XzlCorpExceptionEnum.INVITE_NOT_EXIST);
    }
  }

  @Override
  public ApiRestResponse bindUserWithOrganizationAndProject(BindUserRequest request) {
    LambdaQueryWrapper<Invite> queryWrapper = new LambdaQueryWrapper<>();
    queryWrapper.eq(Invite::getUuid, request.getUuid());
    Invite invite = this.getOne(queryWrapper);
    User user = userService.getUserInfoById(request.getUserId());
    List<Integer> userOrganizations = ArrayToList.toList(user.getOrganizations());
    userOrganizations = UniqueList.toUnique(userOrganizations, Arrays.asList(invite.getOrganization()));
    user.setOrganizations(userOrganizations.toArray(new Integer[userOrganizations.size()]));
    List<Integer> userProjects = ArrayToList.toList(user.getProjects());
    userProjects = UniqueList.toUnique(userProjects, Arrays.asList(invite.getProjects()));
    user.setProjects(userProjects.toArray(new Integer[userProjects.size()]));
    userService.updateUser(user);

    Integer[] projectIds = invite.getProjects();
    Integer organizationId = invite.getOrganization();
    Integer userId = request.getUserId();
    organizationService.addUser(organizationId, Arrays.asList(userId));
    Arrays.asList(projectIds).forEach(projectId -> {
      projectService.addUser(projectId, Arrays.asList(userId));
    });
    return ApiRestResponse.success();
  }
}




