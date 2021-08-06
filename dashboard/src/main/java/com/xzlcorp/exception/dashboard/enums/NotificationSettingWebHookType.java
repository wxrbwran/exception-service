package com.xzlcorp.exception.dashboard.enums;

import lombok.Data;

/**
 * @author wuxiaoran
 */

public enum NotificationSettingWebHookType {

  DINGTALK(1, "dingtalk"),
  WECHATWORK(2, "wechat_work"),
  OTHERS(3, "others"),
  ;

  private Integer code;

  private String description;

  NotificationSettingWebHookType(Integer code, String description) {
    this.code = code;
    this.description = description;
  }


}
