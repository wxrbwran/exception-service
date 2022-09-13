package com.xzlcorp.exception.manager.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xzlcorp.exception.manager.model.pojo.Issue;
import org.apache.ibatis.annotations.Mapper;

/**
* @author wxr
* @description 针对表【t_issue】的数据库操作Mapper
* @createDate 2022-04-12 22:59:00
* @Entity
*/
@Mapper
public interface IssueMapper extends BaseMapper<Issue> {

}




