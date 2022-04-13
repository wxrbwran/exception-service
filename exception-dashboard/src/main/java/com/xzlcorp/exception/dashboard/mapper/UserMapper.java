package com.xzlcorp.exception.dashboard.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xzlcorp.exception.dashboard.model.pojo.User;
import org.apache.ibatis.annotations.Mapper;

/**
* @author wuxiaoran
* @description 针对表【t_user】的数据库操作Mapper
* @createDate 2022-04-13 11:34:48
* @Entity com.xzlcorp.exception.dashboard.domain.Auth
*/
@Mapper
public interface UserMapper extends BaseMapper<User> {

}




