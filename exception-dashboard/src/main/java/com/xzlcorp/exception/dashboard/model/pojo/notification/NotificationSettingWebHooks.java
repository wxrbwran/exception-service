package com.xzlcorp.exception.dashboard.model.pojo.notification;

import com.xzlcorp.exception.dashboard.enums.NotificationSettingWebHookType;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;

/**
 * @author wuxiaoran
 */
@Data
public class NotificationSettingWebHooks {

  List<NotificationSettingWebHook> notificationSettingWebHooks = new ArrayList<>();

}
