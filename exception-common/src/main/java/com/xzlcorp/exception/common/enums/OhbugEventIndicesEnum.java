package com.xzlcorp.exception.common.enums;


/**
 * @author wuxiaoran
 */

public enum OhbugEventIndicesEnum {

  ERROR("error", "ohbug-event-error*", "ohbug-event-error"),
  PERFORMANCE("performance", "ohbug-performance*", "ohbug-performance"),
  ;


  private String category;
  private String key;
  private String index;

  OhbugEventIndicesEnum(String category, String key, String index) {
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
