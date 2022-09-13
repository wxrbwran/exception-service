package com.xzlcorp.exception.dashboard.mapper;

import com.xzlcorp.exception.dashboard.model.pojo.Organization;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author wuxiaoran
* @description 针对表【t_organization(组织表)】的数据库操作Mapper
* @createDate 2022-04-13 11:38:57
* @Entity com.xzlcorp.exception.dashboard.model.pojo.Organization
*/
@Mapper

public interface OrganizationMapper extends BaseMapper<Organization> {

}




