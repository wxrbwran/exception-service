package com.xzlcorp.exception.common.common;

/**
 * 描述: 常量
 *
 * @author wuxiaoran
 */
public class Constant {

  public static final String defaultPageAt = "1";
  public static final String defaultPageSize = "10";

  public static final String SALT = "lerna$%^&*923";

  public static final String APP_SECRET = "nobugs@free";

  public static final String EVENT_QUEUE = "EVENT_QUEUE";



  public static final Integer MAX_USERS_NUMBER = 1000;
  public static final Integer MAX_ISSUES_NUMBER = 500;

  public static final String TWO_WEEK = "14d";
  public static final String ONE_DAY = "24h";
  public static final String ALL = "all";

  public static final long ONE_DAY_MILLS_SECOND = 86400000;

//  rabbit
  public static final String MQ_EXCHANGE_NAME = "exception.service";
  public static final String MQ_QUEUE_NAME = "event";
  public static final String MQ_TOPIC_KEY = "event.#";

}
