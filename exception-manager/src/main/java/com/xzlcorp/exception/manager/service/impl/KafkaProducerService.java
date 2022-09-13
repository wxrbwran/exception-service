package com.xzlcorp.exception.manager.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFutureCallback;

/**
 * @author wuxiaoran
 */
@Component
public class KafkaProducerService {

  @Autowired
  private KafkaTemplate<String, String> kafkaTemplate;

  public void sendMessage(String topic, String message) {
    kafkaTemplate.send(topic, message);
  }

  public void sendMessageWithCallback(
      String topic,
      String message,
      ListenableFutureCallback<SendResult<String, String>> callback
  ) {
    kafkaTemplate.send(topic, message).addCallback(callback);
  }
}

