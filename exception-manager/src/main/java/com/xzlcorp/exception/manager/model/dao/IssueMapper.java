package com.xzlcorp.exception.manager.model.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xzlcorp.exception.manager.model.pojo.Issue;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IssueMapper extends BaseMapper<Issue> {
}
