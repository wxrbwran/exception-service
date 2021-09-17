package com.xzlcorp.exception.common.common;

public class Topics {

  public static final String TOPIC_MANAGER_LOGSTASH_EVENT_ERROR =
      "TOPIC_MANAGER_LOGSTASH_EVENT_ERROR";
  public static final String TOPIC_MANAGER_LOGSTASH_EVENT_MESSAGE =
      "TOPIC_MANAGER_LOGSTASH_EVENT_MESSAGE";
  public static final String TOPIC_MANAGER_LOGSTASH_EVENT_FEEDBACK =
      "TOPIC_MANAGER_LOGSTASH_EVENT_FEEDBACK";
  public static final String TOPIC_MANAGER_LOGSTASH_EVENT_VIEW =
      "TOPIC_MANAGER_LOGSTASH_EVENT_VIEW";
  // performance
  public static final String TOPIC_MANAGER_LOGSTASH_PERFORMANCE =
      "TOPIC_MANAGER_LOGSTASH_PERFORMANCE";

  // transfer 接收 logstash 的回调后，传递给 manager 准备聚合任务
  public static final String TOPIC_TRANSFER_MANAGER_EVENT = "TOPIC_TRANSFER_MANAGER_EVENT";

  // dashboard 向 manager 查询 issue
  public static final String TOPIC_DASHBOARD_MANAGER_GET_ISSUE =
      "TOPIC_DASHBOARD_MANAGER_GET_ISSUE";
  // dashboard 向 manager 搜索 issues
  public static final String TOPIC_DASHBOARD_MANAGER_SEARCH_ISSUES =
      "TOPIC_DASHBOARD_MANAGER_SEARCH_ISSUES";
  // dashboard 向 manager 获取 trend
  public static final String TOPIC_DASHBOARD_MANAGER_GET_TREND =
      "TOPIC_DASHBOARD_MANAGER_GET_TREND";
  // dashboard 向 manager getLatestEventByIssueId
  public static final String TOPIC_DASHBOARD_MANAGER_GET_LATEST_EVENT =
      "TOPIC_DASHBOARD_MANAGER_GET_LATEST_EVENT";
  // dashboard 向 manager getEventByEventId
  public static final String TOPIC_DASHBOARD_MANAGER_GET_EVENT =
      "TOPIC_DASHBOARD_MANAGER_GET_EVENT";
  // dashboard 向 manager 查询 project 对应的趋势图
  public static final String TOPIC_DASHBOARD_MANAGER_GET_PROJECT_TREND =
      "TOPIC_DASHBOARD_MANAGER_GET_PROJECT_TREND";

  // manager 向 notifier 派发通知任务
  public static final String TOPIC_MANAGER_NOTIFIER_DISPATCH_NOTICE =
      "TOPIC_MANAGER_NOTIFIER_DISPATCH_NOTICE";
  // dashboard 想 notifier 派发发送邮件任务
  public static final String TOPIC_DASHBOARD_NOTIFIER_SEND_EMAIL =
      "TOPIC_DASHBOARD_NOTIFIER_SEND_EMAIL";
}
