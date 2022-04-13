package com.xzlcorp.exception.dashboard.mapper;

import com.xzlcorp.exception.dashboard.model.pojo.Invite;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author wuxiaoran
* @description 针对表【t_invite(邀请表)】的数据库操作Mapper
* @createDate 2022-04-13 11:32:48
* @Entity com.xzlcorp.exception.dashboard.model.pojo.Invite
*/
@Mapper
public interface InviteMapper extends BaseMapper<Invite> {

}




