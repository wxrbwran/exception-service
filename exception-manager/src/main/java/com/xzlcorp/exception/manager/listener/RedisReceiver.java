package com.xzlcorp.exception.manager.listener;

import com.alibaba.fastjson.JSON;
import com.xzlcorp.exception.common.model.pojo.event.Event;
import com.xzlcorp.exception.manager.service.EventService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;

/**
 * @author wuxiaoran
 */
@Component
@Slf4j
public class RedisReceiver implements MessageListener {

  @Autowired
  private EventService eventService;
  @SneakyThrows
  @Override
  public void onMessage(Message message, byte[] pattern) {
//    log.info("onMessage message: {}", message);
//    log.info("onMessage message.toString: {}", message.toString());

//    Event event = (Event) message.getBody();
//    log.info("onMessage event: {}", event.toString());
    Event event = JSON.parseObject(message.toString(), Event.class);
    log.info("onMessage event received");
    eventService.handleEvent(event);
//    log.info("onMessage getChannel: {}", message.getChannel());

  }
}