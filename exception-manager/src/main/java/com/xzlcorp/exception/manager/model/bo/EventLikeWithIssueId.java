package com.xzlcorp.exception.manager.model.bo;

import com.xzlcorp.exception.common.model.pojo.event.Event;
import lombok.Data;

/**
 * @author wuxiaoran
 */
@Data
public class EventLikeWithIssueId {
  private Event event;

  private Integer issueId;

}
