package com.xzlcorp.exception.manager.enums;

import lombok.Data;


public enum IssueTrendPeriodEnum {

  TwoWeek("14d"),
  OneDay("24h"),
  ALL("all")
  ;

  private String desc;

  public String getDesc() {
    return desc;
  }

  public void setDesc(String desc) {
    this.desc = desc;
  }

  IssueTrendPeriodEnum(String desc){
    this.desc = desc;
  }
}
