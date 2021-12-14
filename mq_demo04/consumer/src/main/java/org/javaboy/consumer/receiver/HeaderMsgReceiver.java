package org.javaboy.consumer.receiver;

import org.javaboy.consumer.config.HeaderConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author 江南一点雨
 * @微信公众号 江南一点雨
 * @网站 http://www.itboyhub.com
 * @国际站 http://www.javaboy.org
 * @微信 a_java_boy
 * @GitHub https://github.com/lenve
 * @Gitee https://gitee.com/lenve
 */
@Component
public class HeaderMsgReceiver {

    @RabbitListener(queues = HeaderConfig.HEADER_QUEUE_NAME_NAME)
    public void nameMsgHandler(byte[] msg) {
        System.out.println("nameMsgHandler >>> " + new String(msg, 0, msg.length));
    }
    @RabbitListener(queues = HeaderConfig.HEADER_QUEUE_AGE_NAME)
    public void ageMsgHandler(byte[] msg) {
        System.out.println("ageMsgHandler >>> " + new String(msg, 0, msg.length));
    }
}
