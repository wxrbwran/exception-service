package com.xzlcorp.exception.transfer.mq.spring;

import com.xzlcorp.exception.common.common.Constant;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class  SpringAmqpTest {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void testSendMessage2SimpleQueue() {
        String queueName = "simple.queue";
        String message = "hello, spring amqp!";
        rabbitTemplate.convertAndSend(queueName, message);
    }

    @Test
    public void testSendMessage2WorkQueue() throws InterruptedException {
        String queueName = "simple.queue";
        String message = "hello, message__";
        for (int i = 1; i <= 50; i++) {
            rabbitTemplate.convertAndSend(queueName, message + i);
            Thread.sleep(20);
        }
    }

    @Test
    public void testSendFanoutExchange() {
        // 交换机名称
        String exchangeName = "itcast.fanout";
        // 消息
        String message = "hello, every one!";
        // 发送消息
        rabbitTemplate.convertAndSend(exchangeName, "", message);
    }

    @Test
    public void testSendDirectExchange() {
        // 交换机名称
        String exchangeName = "itcast.direct";
        // 消息
        String message = "hello, red!";
        // 发送消息
        rabbitTemplate.convertAndSend(exchangeName, "red", message);
    }

    @Test
    public void testSendTopicExchange() {
        // 交换机名称
        String exchangeName = "itcast.topic";
        // 消息
        String message = "今天天气不错，我的心情好极了!";
        // 发送消息
        rabbitTemplate.convertAndSend(exchangeName, "china.weather", message);
    }

    @Test
    public void testSendTopicExchangeExceptionService() {
        // 交换机名称
        String exchangeName = Constant.MQ_EXCHANGE_NAME;
        // 消息
        String message = "今天天气不错，我的心情好极了!";
        // 发送消息
        rabbitTemplate.convertAndSend(exchangeName, Constant.MQ_TOPIC_KEY, message);
    }

    @Test
    public void testSendMessage2ObjectQueue() {
        String queueName = "object.queue";
        Map<String, Object> message = new HashMap<>();
        message.put("name", "留言");
        message.put("age", 31);
        rabbitTemplate.convertAndSend(queueName, message);
    }

    // 发布者ack

    @Test
    public void testSendMessage2SimpleQueueAdvance() throws InterruptedException {
        // 1.准备消息
        String message = "hello, spring amqp!";
        // 2.准备CorrelationData
        // 2.1.消息ID
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        // 2.2.准备ConfirmCallback
        correlationData.getFuture().addCallback(result -> {
            // 判断结果
            if (result.isAck()) {
                // ACK
                log.debug("消息成功投递到交换机！消息ID: {}", correlationData.getId());
            } else {
                // NACK
                log.error("消息投递到交换机失败！消息ID：{}", correlationData.getId());
                // 重发消息
            }
        }, ex -> {
            // 记录日志
            log.error("消息发送失败！", ex);
            // 重发消息
        });
        // 3.发送消息
        rabbitTemplate.convertAndSend("amq.topic", "a.simple.test", message, correlationData);
    }

    @Test
    public void testDurableMessage() {
        // 1.准备消息
        Message message = MessageBuilder.withBody("hello, spring".getBytes(StandardCharsets.UTF_8))
            .setDeliveryMode(MessageDeliveryMode.PERSISTENT)
            .build();
        // 2.发送消息
        rabbitTemplate.convertAndSend("simple.queue", message);
    }

    @Test
    public void testTTLMessage() {
        // 1.准备消息
        Message message = MessageBuilder
            .withBody("hello, ttl messsage".getBytes(StandardCharsets.UTF_8))
            .setDeliveryMode(MessageDeliveryMode.PERSISTENT)
            .setExpiration("5000")
            .build();
        // 2.发送消息
        rabbitTemplate.convertAndSend("ttl.direct", "ttl", message);
        // 3.记录日志
        log.info("消息已经成功发送！");
    }

    @Test
    public void testSendDelayMessage() throws InterruptedException {
        // 1.准备消息
        Message message = MessageBuilder
            .withBody("hello, ttl messsage".getBytes(StandardCharsets.UTF_8))
            .setDeliveryMode(MessageDeliveryMode.PERSISTENT)
            .setHeader("x-delay", 5000)
            .build();
        // 2.准备CorrelationData
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        // 3.发送消息
        rabbitTemplate.convertAndSend("delay.direct", "delay", message, correlationData);

        log.info("发送消息成功");
    }

    @Test
    public void testLazyQueue() throws InterruptedException {
        long b = System.nanoTime();
        for (int i = 0; i < 1000000; i++) {
            // 1.准备消息
            Message message = MessageBuilder
                .withBody("hello, Spring".getBytes(StandardCharsets.UTF_8))
                .setDeliveryMode(MessageDeliveryMode.NON_PERSISTENT)
                .build();
            // 2.发送消息
            rabbitTemplate.convertAndSend("lazy.queue", message);
        }
        long e = System.nanoTime();
        System.out.println(e - b);
    }
    @Test
    public void testNormalQueue() throws InterruptedException {
        long b = System.nanoTime();
        for (int i = 0; i < 1000000; i++) {
            // 1.准备消息
            Message message = MessageBuilder
                .withBody("hello, Spring".getBytes(StandardCharsets.UTF_8))
                .setDeliveryMode(MessageDeliveryMode.NON_PERSISTENT)
                .build();
            // 2.发送消息
            rabbitTemplate.convertAndSend("normal.queue", message);
        }
        long e = System.nanoTime();
        System.out.println(e - b);
    }
}
