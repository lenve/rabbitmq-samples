package org.javaboy.confirm.controller;

import org.javaboy.confirm.config.RabbitConfig;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

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
        rabbitTemplate.convertAndSend(RabbitConfig.JAVABOY_EXCHANGE_NAME, RabbitConfig.JAVABOY_QUEUE_NAME, "hello javaboy!",new CorrelationData(UUID.randomUUID().toString()));
    }
}
