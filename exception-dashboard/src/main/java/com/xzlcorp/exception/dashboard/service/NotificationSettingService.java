package com.xzlcorp.exception.dashboard.service;

import com.xzlcorp.exception.dashboard.model.pojo.NotificationSetting;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xzlcorp.exception.dashboard.model.request.NotificationSettingRequest;

/**
* @author wuxiaoran
* @description 针对表【t_notification_setting(通知设置表)】的数据库操作Service
* @createDate 2022-04-13 15:13:33
*/
public interface NotificationSettingService extends IService<NotificationSetting> {

  NotificationSetting createNotificationSetting(NotificationSettingRequest request);

  NotificationSetting getNotificationSetting(Integer projectId);
}
