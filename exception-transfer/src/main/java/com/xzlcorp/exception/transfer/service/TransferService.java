package com.xzlcorp.exception.transfer.service;

import com.alibaba.fastjson.JSON;
import com.xzlcorp.exception.common.common.Constant;
import com.xzlcorp.exception.common.model.pojo.event.Event;
import com.xzlcorp.exception.common.model.pojo.event.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @author wuxiaoran
 */
@Service
public class TransferService {

  @Autowired
  StringRedisTemplate template;

  public void handleEvent(Event event, String ipAddress) {
    Event newEvent = new Event();
    BeanUtils.copyProperties(event, newEvent);
    User user = newEvent.getUser();
    if (user == null) {
      user = new User();
    }
    user.setIpAddress(ipAddress);
    newEvent.setUser(user);
    String eventString = JSON.toJSONString(newEvent);
    // todo: 改为延迟队列
    template.convertAndSend(Constant.EVENT_QUEUE, eventString);
  }
}
