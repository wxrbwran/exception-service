package com.xzlcorp.exception.transfer.mq.spring;

import com.xzlcorp.exception.transfer.component.RabbitSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AppTest {

  @Autowired
  private RabbitSender rabbitSender;

  @Test
  public void testSender(){
    Map<String, Object> props = new HashMap<>();
    props.put("attr1", "12345");
    props.put("attr2", "gfw");

    rabbitSender.send("hello from test 1", props);
  }
}
