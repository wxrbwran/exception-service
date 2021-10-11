package com.xzlcorp.exception.dashboard.enums;

public enum NotificationRuleLevel {

  SERIOUS("serious"),
  WARNING("warning"),
  DEFAULT("default"),
  ;

  private String description;

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  NotificationRuleLevel(String description) {
    this.description = description;
  }
}
