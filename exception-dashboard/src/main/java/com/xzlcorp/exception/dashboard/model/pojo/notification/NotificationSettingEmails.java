package com.xzlcorp.exception.dashboard.model.pojo.notification;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;

/**
 * @author wuxiaoran
 */
@Data
public class NotificationSettingEmails {

  private List<NotificationSettingEmail> emails = new ArrayList<>();

}
