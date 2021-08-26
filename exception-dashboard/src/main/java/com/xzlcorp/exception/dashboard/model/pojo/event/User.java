package com.xzlcorp.exception.dashboard.model.pojo.event;

import java.util.Map;
import lombok.Data;

/**
 * @author wuxiaoran
 */
@Data
public class User {

  private String uuid;

  private String ipAddress;

  private String id;

  private String name;

  private String email;

  private Map<String, Object> other;

}
