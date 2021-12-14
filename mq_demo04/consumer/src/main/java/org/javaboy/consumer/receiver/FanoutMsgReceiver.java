package org.javaboy.consumer.receiver;

import org.javaboy.consumer.config.FanoutConfig;
import org.javaboy.consumer.config.RabbitConfig;
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
public class FanoutMsgReceiver {
    @RabbitListener(queues = FanoutConfig.FANOUT_QUEUE_NAME)
    public void msgHandler(String msg) {
        System.out.println("FanoutMsgReceiver = " + msg);
    }

    @RabbitListener(queues = FanoutConfig.FANOUT_QUEUE_NAME2)
    public void msgHandler2(String msg) {
        System.out.println("FanoutMsgReceiver2 = " + msg);
    }

}
