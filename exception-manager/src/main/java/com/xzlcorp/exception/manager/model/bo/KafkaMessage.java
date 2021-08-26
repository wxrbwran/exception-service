package com.xzlcorp.exception.manager.model.bo;

import lombok.Data;

@Data
public class KafkaMessage {
  private String key;

  private EventLikeWithIssueId event;
}
