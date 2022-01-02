package org.javaboy.message_delay_dlx.controller;

import org.javaboy.message_delay_dlx.config.RabbitConfig;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

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
        Message msg = MessageBuilder.withBody(("hello javaboy" + new Date()).getBytes()).build();
        rabbitTemplate.send(RabbitConfig.JAVABOY_EXCHANGE_NAME, RabbitConfig.JAVABOY_QUEUE_NAME, msg);
    }
}
