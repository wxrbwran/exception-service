package com.xzlcorp.exception.transfer.service;

import com.alibaba.fastjson.JSON;
import com.xzlcorp.exception.common.common.Constant;
import com.xzlcorp.exception.common.model.pojo.event.Event;
import com.xzlcorp.exception.common.model.pojo.event.User;
import com.xzlcorp.exception.transfer.feign.ManagerFeignClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author wuxiaoran
 */
@Service
@Slf4j
public class TransferService {

  // todo: 不实用redis pubsub模式，不靠谱，无确认，宕机后也无法获取之前消息
  // 可使用rabbit消息队列

  @Autowired
  private ManagerFeignClient managerFeignClient;

  @Autowired
  private RabbitTemplate rabbitTemplate;

  public void handleEvent(Event event, String ipAddress) {
    Event newEvent = new Event();
    BeanUtils.copyProperties(event, newEvent);
    User user = newEvent.getUser();
    if (user == null) {
      user = new User();
    }
    user.setIpAddress(ipAddress);
    newEvent.setUser(user);
    // 交换机名称
    String exchangeName = Constant.MQ_EXCHANGE_NAME;
    // 消息
    String message = JSON.toJSONString(newEvent);
    // 发送消息
    rabbitTemplate.convertAndSend(exchangeName, Constant.MQ_TOPIC_KEY, message);
//    managerFeignClient.handleTransferEvent(newEvent);
  }
}
