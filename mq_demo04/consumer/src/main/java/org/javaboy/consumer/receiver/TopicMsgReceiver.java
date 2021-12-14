package org.javaboy.consumer.receiver;

import org.javaboy.consumer.config.FanoutConfig;
import org.javaboy.consumer.config.TopicConfig;
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
public class TopicMsgReceiver {
    @RabbitListener(queues = TopicConfig.HUAWEI_QUEUE_NAME)
    public void huawei(String msg) {
        System.out.println("huawei = " + msg);
    }

    @RabbitListener(queues = TopicConfig.XIAOMI_QUEUE_NAME)
    public void xiaomi(String msg) {
        System.out.println("xiaomi = " + msg);
    }

    @RabbitListener(queues = TopicConfig.PHONE_QUEUE_NAME)
    public void phone(String msg) {
        System.out.println("phone = " + msg);
    }

}
