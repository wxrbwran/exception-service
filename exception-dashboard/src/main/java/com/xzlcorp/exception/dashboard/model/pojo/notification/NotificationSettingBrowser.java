package com.xzlcorp.exception.dashboard.model.pojo.notification;

import lombok.Data;

/**
 * @author wuxiaoran
 */
@Data
public class NotificationSettingBrowser {

  private Boolean open;

  private Data data;

  @lombok.Data
  public class Data {

    private String endpoint;

    private String expirationTime;

    private Keys keys;

    @lombok.Data
    public class Keys {

      private String p256dh;

      private String auth;

    }

  }
}
