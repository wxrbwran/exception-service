package com.xzlcorp.exception.dashboard.model.pojo.event;

import com.xzlcorp.exception.dashboard.model.pojo.MetaData;
import lombok.Data;

/**
 * @author wuxiaoran
 */
@Data
public class Event<T> {
  private String apiKey;
  private String apiVersion;
  private String apiType;

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

  private T detail;

  private Device device;

  private User user;



  private MetaData metaData;


}
