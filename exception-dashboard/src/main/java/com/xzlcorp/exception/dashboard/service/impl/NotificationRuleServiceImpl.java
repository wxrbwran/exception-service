package com.xzlcorp.exception.dashboard.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xzlcorp.exception.common.common.ApiRestResponse;
import com.xzlcorp.exception.common.exception.XzlCorpException;
import com.xzlcorp.exception.common.exception.XzlCorpExceptionEnum;
import com.xzlcorp.exception.dashboard.model.pojo.NotificationRule;
import com.xzlcorp.exception.dashboard.mapper.NotificationRuleMapper;
import com.xzlcorp.exception.dashboard.model.request.AddNotificationRuleRequest;
import com.xzlcorp.exception.dashboard.model.request.EditNotificationRuleRequest;
import com.xzlcorp.exception.dashboard.service.NotificationRuleService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author wuxiaoran
* @description 针对表【t_notification_rule(提醒规则表)】的数据库操作Service实现
* @createDate 2022-04-13 15:13:20
*/
@Service
public class NotificationRuleServiceImpl extends ServiceImpl<NotificationRuleMapper, NotificationRule>
    implements NotificationRuleService{
  private final static int MAX_RULES_NUMBER = 10;
  private final static int MAX_EMAILS_NUMBER = 20;
  private final static int MAX_WEBHOOKS_NUMBER = 10;

  public ApiRestResponse createNotificationRule(AddNotificationRuleRequest request) {
    List<NotificationRule> rules = getNotificationRules(request.getProjectId());
    if (rules != null && rules.size() < MAX_RULES_NUMBER) {
      NotificationRule rule = new NotificationRule();
      BeanUtils.copyProperties(request, rule);
      rule.setProject(request.getProjectId());
      rule.setLevel(request.getLevel());
      this.save(rule);
      return ApiRestResponse.success(rule);
    }
    throw new XzlCorpException(XzlCorpExceptionEnum.TOO_MUCH);
  }

  public List<NotificationRule> getNotificationRules(Integer projectId) {
    QueryWrapper<NotificationRule> queryWrapper = new QueryWrapper<>();
    queryWrapper.eq("project", projectId);
    queryWrapper.orderByDesc("id");
    List<NotificationRule> notificationRuleList = this.list(queryWrapper);
    return notificationRuleList;
  }


  public ApiRestResponse updateNotificationRule(Integer ruleId, EditNotificationRuleRequest request) {
    NotificationRule rule = new NotificationRule();
    rule.setId(ruleId);
    BeanUtils.copyProperties(request, rule);
    this.updateById(rule);
    return ApiRestResponse.success(rule);
  }

  public ApiRestResponse deleteNotificationRule(Integer ruleId) {
    this.removeById(ruleId);
    return ApiRestResponse.success(ruleId);
  }

}




