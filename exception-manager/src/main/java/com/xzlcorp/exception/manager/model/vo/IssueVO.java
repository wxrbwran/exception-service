package com.xzlcorp.exception.manager.model.vo;

import com.xzlcorp.exception.common.model.pojo.event.MetaData;
import lombok.Data;

/**
 * @author wuxiaoran
 */
@Data
public class IssueVO {
  private Integer id;

  private String intro;

  private String apiKey;

  private String type;

  private MetaData metadata;

  private Integer[] events;

  private Integer eventsCount;

  private Integer[] users;

  private Integer usersCount;
}
