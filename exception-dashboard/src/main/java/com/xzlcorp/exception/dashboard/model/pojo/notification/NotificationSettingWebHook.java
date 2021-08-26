package com.xzlcorp.exception.dashboard.model.pojo.notification;

import com.xzlcorp.exception.dashboard.enums.NotificationSettingWebHookType;
import java.util.List;
import java.util.Map;
import lombok.Data;

/**
 * @author wuxiaoran
 */
@Data
public class NotificationSettingWebHook {

  private String id;

  private String name;

  private NotificationSettingWebHookType type;

  private String link;

  private Boolean open;

  private List<Map<String, String>> at;

}
