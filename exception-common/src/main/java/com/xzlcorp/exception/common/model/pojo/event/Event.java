package com.xzlcorp.exception.common.model.pojo.event;

import java.util.List;
import lombok.Data;

/**
 * @author wuxiaoran
 */
@Data
public class Event {
  private String apiKey;
  private String appVersion;
  private String appType;
  private String index;
  private String id;

  /**
   *   'development' | 'production'
   */
  private String releaseStage;

  private String timestamp;

  /**
   * 'error' | 'message' | 'feedback' |
   * 'view' | 'performance' | 'other'
   */
  private String category;

  private String type;

  private SDK sdk;

  private Detail detail;

  private Device device;

  private User user;

  private MetaData metaData;

  private List<Action> actions;

  private String previous;
  private String next;


}
