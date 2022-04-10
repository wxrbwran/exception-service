package com.xzlcorp.exception.dashboard.service;

import com.xzlcorp.exception.common.common.ApiRestResponse;
import com.xzlcorp.exception.common.exception.XzlCorpException;
import com.xzlcorp.exception.common.exception.XzlCorpExceptionEnum;
import com.xzlcorp.exception.dashboard.model.pojo.Project;
import com.xzlcorp.exception.dashboard.model.pojo.notification.NotificationRule;
import com.xzlcorp.exception.dashboard.model.pojo.notification.NotificationSetting;
import com.xzlcorp.exception.dashboard.model.request.AddNotificationRuleRequest;
import com.xzlcorp.exception.dashboard.model.request.EditNotificationRuleRequest;
import com.xzlcorp.exception.dashboard.model.request.NotificationSettingRequest;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wuxiaoran
 */
@Service
public class NotificationService {

  private final static int MAX_RULES_NUMBER = 10;
  private final static int MAX_EMAILS_NUMBER = 20;
  private final static int MAX_WEBHOOKS_NUMBER = 10;

//  @Autowired
//  private NotificationSettingMapper notificationSettingMapper;
//
//  @Autowired
//  private NotificationRuleMapper notificationRuleMapper;

  @Autowired
  private ProjectService projectService;

  public NotificationSetting createNotificationSetting(NotificationSettingRequest request) {
    NotificationSetting notificationSetting = new NotificationSetting();
    BeanUtils.copyProperties(request, notificationSetting);
//    notificationSettingMapper.insertSelective(notificationSetting);
    return notificationSetting;
  }

  public ApiRestResponse createNotificationRule(AddNotificationRuleRequest request) {
    List<NotificationRule> rules = getNotificationRules(request.getProjectId());
    if (rules != null && rules.size() < MAX_RULES_NUMBER) {
      NotificationRule rule = new NotificationRule();
      BeanUtils.copyProperties(request, rule);
      rule.setProject(request.getProjectId());
      rule.setLevel(request.getLevel());
//      notificationRuleMapper.insertSelective(rule);
      return ApiRestResponse.success(rule);
    }
    throw new XzlCorpException(XzlCorpExceptionEnum.TOO_MUCH);
  }

  public List<NotificationRule> getNotificationRules(Integer projectId) {
//    List<NotificationRule> notificationRuleList = notificationRuleMapper.select(c ->
//      c.where(NotificationRuleDynamicSqlSupport.project, isEqualTo(projectId))
//          .orderBy(NotificationRuleDynamicSqlSupport.id)
//    );
//    return notificationRuleList;
    return null;
  }


  public ApiRestResponse updateNotificationRule(Integer ruleId, EditNotificationRuleRequest request) {
    NotificationRule rule = new NotificationRule();
    rule.setId(ruleId);
    BeanUtils.copyProperties(request, rule);
//    notificationRuleMapper.updateByPrimaryKeySelective(rule);
    return ApiRestResponse.success(rule);
  }

  public ApiRestResponse deleteNotificationRule(Integer ruleId) {
//    notificationRuleMapper.deleteByPrimaryKey(ruleId);
    return ApiRestResponse.success(ruleId);
  }

  public NotificationSetting getNotificationSetting(Integer projectId) {
    Project project = projectService.getProjectById(projectId);
//    NotificationSetting setting = notificationSettingMapper.selectOne(c ->
//        c.where(NotificationRuleDynamicSqlSupport.id, isEqualTo(project.getNotificationSetting())));
//    return setting;
    return null;
  }
}
