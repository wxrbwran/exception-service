package com.xzlcorp.exception.dashboard.mapper;

import com.xzlcorp.exception.dashboard.model.pojo.Project;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author wuxiaoran
* @description 针对表【t_project(项目表)】的数据库操作Mapper
* @createDate 2022-04-13 11:39:29
* @Entity com.xzlcorp.exception.dashboard.model.pojo.Project
*/
@Mapper

public interface ProjectMapper extends BaseMapper<Project> {

}




