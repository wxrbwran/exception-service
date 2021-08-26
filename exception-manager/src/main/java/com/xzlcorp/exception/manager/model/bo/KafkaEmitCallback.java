package com.xzlcorp.exception.manager.model.bo;

import lombok.Data;

@Data
public class KafkaEmitCallback {

  private String topicName;
  private String partition;
  private String errorCode;
  private String baseOffset;
  private String logAppendTime;
  private String logStartOffset;

}
