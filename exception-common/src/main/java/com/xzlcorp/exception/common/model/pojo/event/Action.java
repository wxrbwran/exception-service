package com.xzlcorp.exception.common.model.pojo.event;

import java.util.Map;
import lombok.Data;

/**
 * @author wuxiaoran
 */
@Data
public class Action {
  private String type;

  private String timestamp;

  private String message;

  private Map<String, Object> data;
}
