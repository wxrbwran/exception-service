package com.xzlcorp.exception.common.enums;


/**
 * @author wuxiaoran
 */

public enum EventIndicesEnum {

  ERROR("error", "EVENT_ERROR", "ohbug-event-error"),
  MESSAGE("error", "EVENT_MESSAGE", "bug-event-message"),
  FEEDBACK("feedback", "EVENT_FEEDBACK", "bug-event-feedback"),
  PERFORMANCE("performance", "EVENT_PERFORMANCE", "ohbug-performance"),
  VIEW("view", "EVENT_VIEW", "bug-event-view"),
  ;


  private String category;
  private String key;
  private String index;

  EventIndicesEnum(String category, String key, String index) {
    this.category = category;
    this.key = key;
    this.index = index;
  }

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public String getKey() {
    return key;
  }

  public void setKey(String key) {
    this.key = key;
  }

  public String getIndex() {
    return index;
  }

  public void setIndex(String index) {
    this.index = index;
  }
}
