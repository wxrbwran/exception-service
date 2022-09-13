package com.xzlcorp.exception.dashboard.service;

import com.xzlcorp.exception.common.common.ApiRestResponse;
import com.xzlcorp.exception.dashboard.model.pojo.Invite;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xzlcorp.exception.dashboard.model.request.BindUserRequest;
import com.xzlcorp.exception.dashboard.model.request.InviteRequest;
import com.xzlcorp.exception.dashboard.model.vo.InviteVO;

import java.security.NoSuchAlgorithmException;

/**
* @author wuxiaoran
* @description 针对表【t_invite(邀请表)】的数据库操作Service
* @createDate 2022-04-13 11:32:48
*/
public interface InviteService extends IService<Invite> {

  ApiRestResponse createInviteUrl(InviteRequest request) throws NoSuchAlgorithmException;


  InviteVO getInviteByUUID(String uuid);


  ApiRestResponse bindUserWithOrganizationAndProject(BindUserRequest request);
}
