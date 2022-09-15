package com.xzlcorp.exception.manager.listener;

import com.alibaba.fastjson.JSON;
import com.xzlcorp.exception.common.common.Constant;
import com.xzlcorp.exception.common.model.pojo.event.Event;
import com.xzlcorp.exception.manager.service.impl.EventService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.security.NoSuchAlgorithmException;

@Component
 @Slf4j
public class SpringRabbitListener {

    @Autowired
    private EventService eventService;

    // @RabbitListener(queues = "simple.queue")
    // public void listenSimpleQueue(String msg) {
    //     System.out.println("消费者接收到simple.queue的消息：【" + msg + "】");
    // }

//    @RabbitListener(queues = "simple.queue")
//    public void listenWorkQueue1(String msg) throws InterruptedException {
//        System.out.println("消费者1接收到消息：【" + msg + "】" + LocalTime.now());
//        Thread.sleep(20);
//    }
//
//    @RabbitListener(queues = "simple.queue")
//    public void listenWorkQueue2(String msg) throws InterruptedException {
//        System.err.println("消费者2........接收到消息：【" + msg + "】" + LocalTime.now());
//        Thread.sleep(200);
//    }

//    @RabbitListener(queues = "fanout.queue1")
//    public void listenFanoutQueue1(String msg) {
//        System.out.println("消费者接收到fanout.queue1的消息：【" + msg + "】");
//    }
//    @RabbitListener(queues = "fanout.queue2")
//    public void listenFanoutQueue2(String msg) {
//        System.out.println("消费者接收到fanout.queue2的消息：【" + msg + "】");
//    }
//
//    @RabbitListener(bindings = @QueueBinding(
//            value = @Queue(name = "direct.queue1"),
//            exchange = @Exchange(name = "itcast.direct", type = ExchangeTypes.DIRECT),
//            key = {"red", "blue"}
//    ))
//    public void listenDirectQueue1(String msg){
//        System.out.println("消费者接收到direct.queue1的消息：【" + msg + "】");
//    }
//
//    @RabbitListener(bindings = @QueueBinding(
//            value = @Queue(name = "direct.queue2"),
//            exchange = @Exchange(name = "itcast.direct", type = ExchangeTypes.DIRECT),
//            key = {"red", "yellow"}
//    ))
//    public void listenDirectQueue2(String msg){
//        System.out.println("消费者接收到direct.queue2的消息：【" + msg + "】");
//    }
//
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = Constant.MQ_QUEUE_NAME),
            exchange = @Exchange(name = Constant.MQ_EXCHANGE_NAME, type = ExchangeTypes.TOPIC),
            key = Constant.MQ_TOPIC_KEY
    ))
    public void listenTopicTransferEvent(String msg) {
        System.out.println("消费者接收到" + Constant.MQ_TOPIC_KEY +"的消息：【" + msg + "】");
        Event event = JSON.parseObject(msg, Event.class);
        log.info("mq消息转换后的对象，{}", event);
        try {
            eventService.handleEvent(event);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
//
//    @RabbitListener(bindings = @QueueBinding(
//            value = @Queue(name = "topic.queue2"),
//            exchange = @Exchange(name = "itcast.topic", type = ExchangeTypes.TOPIC),
//            key = "#.news"
//    ))
//    public void listenTopicQueue2(String msg){
//        System.out.println("消费者接收到topic.queue2的消息：【" + msg + "】");
//    }
//
//    @RabbitListener(queues = "object.queue")
//    public void listenObjectQueue(Map<String,Object> msg){
//        System.out.println("接收到object.queue的消息：" + msg);
//    }
//
//
//
//   @RabbitListener(queues = "simple.queue")
//    public void listenSimpleQueue(String msg) {
//        log.debug("消费者接收到simple.queue的消息：【" + msg + "】");
//        System.out.println(1 / 0);
//        log.info("消费者处理消息成功！");
//    }


//    @RabbitListener(bindings = @QueueBinding(
//        value = @Queue(name = "dl.queue", durable = "true"),
//        exchange = @Exchange(name = "dl.direct"),
//        key = "dl"
//    ))
//    public void listenDlQueue(String msg) {
//        log.info("消费者接收到了dl.queue的延迟消息");
//    }

//    @RabbitListener(bindings = @QueueBinding(
//            value = @Queue(name = "delay.queue", durable = "true"),
//            exchange = @Exchange(name = "delay.direct", delayed = "true"),
//            key = "delay"
//    ))
//    public void listenDelayExchange(String msg) {
//        log.info("消费者接收到了delay.queue的延迟消息");
//    }
//
//    @RabbitListener(queues = "lazy.queue")
//    public void listenLazyQueue(String msg) {
//        System.out.println("消费者接收到lazy.queue的消息：【" + msg + "】");
//    }
}
