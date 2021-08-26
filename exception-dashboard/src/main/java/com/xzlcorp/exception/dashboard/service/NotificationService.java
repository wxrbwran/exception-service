package com.xzlcorp.exception.dashboard.service;

import com.xzlcorp.exception.dashboard.model.dao.NotificationSettingMapper;
import com.xzlcorp.exception.dashboard.model.pojo.notification.NotificationSetting;
import com.xzlcorp.exception.dashboard.model.request.NotificationSettingRequest;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author wuxiaoran
 */
@Service
public class NotificationService {

  @Autowired
  NotificationSettingMapper notificationSettingMapper;

  public NotificationSetting createNotificationSetting(NotificationSettingRequest request) {
    NotificationSetting notificationSetting = new NotificationSetting();
    BeanUtils.copyProperties(request, notificationSetting);
    notificationSettingMapper.insertSelective(notificationSetting);
    return notificationSetting;
  }
}
