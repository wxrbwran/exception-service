package com.xzlcorp.exception.dashboard.mapper;

import com.xzlcorp.exception.dashboard.model.pojo.NotificationRule;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author wuxiaoran
* @description 针对表【t_notification_rule(提醒规则表)】的数据库操作Mapper
* @createDate 2022-04-13 15:13:20
* @Entity com.xzlcorp.exception.dashboard.model.pojo.NotificationRule
*/
@Mapper
public interface NotificationRuleMapper extends BaseMapper<NotificationRule> {
}




