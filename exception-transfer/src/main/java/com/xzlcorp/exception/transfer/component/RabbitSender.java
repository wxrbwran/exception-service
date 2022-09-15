package com.xzlcorp.exception.transfer.component;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.UUID;

@Component
@Slf4j
public class RabbitSender {
  @Autowired
  private RabbitTemplate rabbitTemplate;


  /**
   * 确认消息的回调监听端口，用于确认
   */
  final RabbitTemplate.ConfirmCallback confirmCallback = new RabbitTemplate.ConfirmCallback() {
    /**
     *
     * @param correlationData 唯一标识
     * @param ack 是否到达成功
     * @param cause 失败的异常信息
     */
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
      log.info("ConfirmCallback confirm correlationData: {}", correlationData);
      log.info("ConfirmCallback confirm ack: {}", ack);
      log.info("ConfirmCallback confirm cause: {}", cause);

    }
  };

  /**
   *
   * @param message 具体消息
   * @param properties 额外的附加属性
   */
  public void send(Object message, Map<String, Object> properties) {
    MessageHeaders messageHeaders = new MessageHeaders(properties);

    Message msg = MessageBuilder.createMessage(message, messageHeaders);

    CorrelationData cd = new CorrelationData(UUID.randomUUID().toString());

    rabbitTemplate.setConfirmCallback(confirmCallback);

    MessagePostProcessor mpp = new MessagePostProcessor() {
      @Override
      public org.springframework.amqp.core.Message postProcessMessage(org.springframework.amqp.core.Message message) throws AmqpException {
        log.info("postProcessMessage message : {}", message);

        return message;
      }
    };

    rabbitTemplate.convertAndSend(
        "exception.service",
        "event.error",
        msg,
        mpp,
        cd
      );
  }
}
