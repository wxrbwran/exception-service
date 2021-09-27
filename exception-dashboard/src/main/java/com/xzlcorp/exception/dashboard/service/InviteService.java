package com.xzlcorp.exception.dashboard.service;

import cn.hutool.core.lang.generator.UUIDGenerator;
import com.xzlcorp.exception.common.common.ApiRestResponse;
import com.xzlcorp.exception.common.common.Constant;
import com.xzlcorp.exception.common.exception.XzlCorpException;
import com.xzlcorp.exception.common.exception.XzlCorpExceptionEnum;
import com.xzlcorp.exception.common.utils.Md5Utils;
import com.xzlcorp.exception.dashboard.model.dao.InviteDynamicSqlSupport;
import com.xzlcorp.exception.dashboard.model.dao.InviteMapper;
import com.xzlcorp.exception.dashboard.model.pojo.Invite;
import com.xzlcorp.exception.dashboard.model.pojo.Organization;
import com.xzlcorp.exception.dashboard.model.pojo.User;
import com.xzlcorp.exception.dashboard.model.request.InviteRequest;
import com.xzlcorp.exception.dashboard.model.vo.InviteVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

@Service
@Slf4j
public class InviteService {

  @Autowired
  private InviteMapper inviteMapper;

  @Autowired
  private UserService userService;

  @Autowired
  private OrganizationService organizationService;

  public ApiRestResponse createInviteUrl(InviteRequest request) throws NoSuchAlgorithmException {
    String auth = request.getAuth();
    List<Integer> projects = request.getProjects();
    Integer organizationId = request.getOrganizationId();
    Integer inviterId = request.getInviterId();

    String hash = Md5Utils.getMD5String(auth + "," + projects.toString() + "," + organizationId + "," + inviterId);
    log.info("createInviteUrl hash: {}", hash);
    Invite oldInvite = inviteMapper.selectOne(c -> c.where(InviteDynamicSqlSupport.hash, isEqualTo(hash)));
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
    inviteMapper.insertSelective(invite);
    return ApiRestResponse.success(invite.getUrl());
  }

  public InviteVO getInviteByUUID(String uuid) {
    Invite invite = inviteMapper.selectOne(c ->
        c.where(InviteDynamicSqlSupport.uuid, isEqualTo(uuid)));
    if (invite != null) {
      InviteVO inviteVO = new InviteVO();
      BeanUtils.copyProperties(invite, inviteVO);
      User inviter = userService.getUserInfoById(invite.getInviter());
      Organization organization = organizationService.getOrganizationById(invite.getOrganization());
      inviteVO.setInviter(userService.handleUser2VO(inviter));
      inviteVO.setOrganization(organizationService.handleOrganization2ToVO(organization));
      return inviteVO;
    } else {
      throw new XzlCorpException(XzlCorpExceptionEnum.INVITE_NOT_EXIST);
    }
  }
}
