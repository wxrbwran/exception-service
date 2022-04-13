package com.xzlcorp.exception.dashboard.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xzlcorp.exception.dashboard.model.pojo.NotificationSetting;
import com.xzlcorp.exception.dashboard.mapper.NotificationSettingMapper;
import com.xzlcorp.exception.dashboard.model.pojo.Project;
import com.xzlcorp.exception.dashboard.model.request.NotificationSettingRequest;
import com.xzlcorp.exception.dashboard.service.NotificationSettingService;
import com.xzlcorp.exception.dashboard.service.ProjectService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* @author wuxiaoran
* @description 针对表【t_notification_setting(通知设置表)】的数据库操作Service实现
* @createDate 2022-04-13 15:13:33
*/
@Service
public class NotificationSettingServiceImpl extends ServiceImpl<NotificationSettingMapper, NotificationSetting>
    implements NotificationSettingService{

  @Autowired
  private ProjectService projectService;

  @Override
  public NotificationSetting createNotificationSetting(NotificationSettingRequest request) {
    NotificationSetting notificationSetting = new NotificationSetting();
    BeanUtils.copyProperties(request, notificationSetting);
    this.save(notificationSetting);
    return notificationSetting;
  }


  @Override
  public NotificationSetting getNotificationSetting(Integer projectId) {
    Project project = projectService.getProjectById(projectId);
    NotificationSetting setting = this.getById(project.getNotificationSetting());
    return setting;
  }
}




