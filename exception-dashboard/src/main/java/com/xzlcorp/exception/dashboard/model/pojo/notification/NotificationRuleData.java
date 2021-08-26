package com.xzlcorp.exception.dashboard.model.pojo.notification;

import lombok.Data;

@Data
public class NotificationRuleData {

  /**
   * 间隔时间
   */
  private Integer interval;
  /**
   * 增长百分比
   */
  private Integer percentage;

  private Integer range1;
  private Integer range2;
  private Integer range3;
  private Integer range4;
}
