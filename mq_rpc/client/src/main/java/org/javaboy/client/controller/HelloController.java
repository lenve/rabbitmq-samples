package org.javaboy.client.controller;

import org.javaboy.client.config.RabbitConfig;
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
    public void hello(String message) {
        //将来要发送的消息对象
        Message msg = MessageBuilder.withBody(message.getBytes()).build();
        //发送消息，方法的返回值就是 server 给出的响应
        Message result = rabbitTemplate.sendAndReceive(RabbitConfig.RPC_EXCHANGE, RabbitConfig.RPC_MSG_QUEUE, msg);
        if (result != null) {
            //发送的消息的 correlationId
            String correlationId = msg.getMessageProperties().getCorrelationId();
            //返回的消息的 correlationId
            String spring_returned_message_correlation = (String) result.getMessageProperties().getHeaders().get("spring_returned_message_correlation");
            if (correlationId.equals(spring_returned_message_correlation)) {
                System.out.println("收到服务端的响应：" + new String(result.getBody()));
            }
        }
    }
}
