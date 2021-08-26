package com.xzlcorp.exception.dashboard.model.request;

import com.xzlcorp.exception.dashboard.model.pojo.notification.NotificationSettingBrowser;
import com.xzlcorp.exception.dashboard.model.pojo.notification.NotificationSettingEmails;
import com.xzlcorp.exception.dashboard.model.pojo.notification.NotificationSettingWebHooks;
import lombok.Data;

/**
 * @author wuxiaoran
 */
@Data
public class NotificationSettingRequest {
  private NotificationSettingEmails emails;

  private NotificationSettingBrowser browser;

  private NotificationSettingWebHooks webhooks;
}
