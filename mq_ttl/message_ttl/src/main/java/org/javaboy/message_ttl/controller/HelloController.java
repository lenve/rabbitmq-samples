package org.javaboy.message_ttl.controller;

import org.javaboy.message_ttl.config.RabbitConfig;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 江南一点雨
 * @微信公众号 江南一点雨
 * @网站 http://www.itboyhub.com
 * @国际站 http://www.javaboy.org
 * @微信 a_java_boy
 * @GitHub https://github.com/lenve
 * @Gitee https://gitee.com/lenve
 */
@RestController
public class HelloController {
    @Autowired
    RabbitTemplate rabbitTemplate;

    @GetMapping("/send")
    public void hello() {
        Message msg = MessageBuilder.withBody("hello javaboy!".getBytes())
                //设置过期时间为10s，消息到达 RabbitMQ 10s 之内，如果没有人消费，则消息会过期
                .setExpiration("10000")
                .build();
        rabbitTemplate.send(RabbitConfig.JAVABOY_MESSSAGE_DELAY_EXCHANGE_NAME, RabbitConfig.JAVABOY_MESSSAGE_DELAY_QUEUE_NAME, msg);
    }
}
