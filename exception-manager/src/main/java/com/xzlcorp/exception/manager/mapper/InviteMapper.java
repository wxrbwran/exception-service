package com.xzlcorp.exception.manager.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xzlcorp.exception.manager.model.pojo.Invite;
import org.apache.ibatis.annotations.Mapper;

/**
* @author wxr
* @description 针对表【t_invite(邀请表)】的数据库操作Mapper
* @createDate 2022-04-12 22:49:58
* @Entity com.xzlcorp.exception.domain.Invite
*/
@Mapper
public interface InviteMapper extends BaseMapper<Invite> {

}




