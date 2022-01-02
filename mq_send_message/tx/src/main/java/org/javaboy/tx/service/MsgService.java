package org.javaboy.tx.service;

import org.javaboy.tx.config.RabbitConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author 江南一点雨
 * @微信公众号 江南一点雨
 * @网站 http://www.itboyhub.com
 * @国际站 http://www.javaboy.org
 * @微信 a_java_boy
 * @GitHub https://github.com/lenve
 * @Gitee https://gitee.com/lenve
 *
 * 1. 客户端发请求，将通信信道设置为事务模式
 * 2。 服务端给出答复，同意将通信信道设置为事务模式
 * 3。 发送消息
 * 4。 提交事务
 * 5。 服务端给出答复，确认事务提交。
 */
@Service
public class MsgService {
    @Autowired
    RabbitTemplate rabbitTemplate;

    @Transactional
    public void send() {
        rabbitTemplate.convertAndSend(RabbitConfig.JAVABOY_MSG_EXCHANGE_NAME, RabbitConfig.JAVABOY_MSG_QUEUE_NAME, "hello javaboy!");
        int i = 1 / 0;
    }
}
