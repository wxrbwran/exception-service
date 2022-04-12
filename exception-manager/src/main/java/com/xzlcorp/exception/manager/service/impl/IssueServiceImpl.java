package com.xzlcorp.exception.manager.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xzlcorp.exception.manager.model.pojo.Issue;
import com.xzlcorp.exception.manager.service.IssueService;
import com.xzlcorp.exception.manager.mapper.IssueMapper;
import org.springframework.stereotype.Service;

/**
* @author wxr
* @description 针对表【t_issue】的数据库操作Service实现
* @createDate 2022-04-12 22:59:00
*/
@Service
public class IssueServiceImpl extends ServiceImpl<IssueMapper, Issue> implements IssueService {
}




