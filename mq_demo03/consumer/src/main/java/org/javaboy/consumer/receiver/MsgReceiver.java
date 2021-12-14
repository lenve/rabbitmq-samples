package org.javaboy.consumer.receiver;

import com.rabbitmq.client.Channel;
import org.javaboy.consumer.config.RabbitConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.io.IOException;

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
public class MsgReceiver {
    @RabbitListener(queues = RabbitConfig.QUEUE_NAME)
    public void handleMsg(Message message, Channel channel) throws IOException {
        System.out.println("receive = " + message.getPayload());
        //确认消息，就是告诉 RabbitMQ，这条消息我已经消费成功了
        channel.basicAck(((Long) message.getHeaders().get(AmqpHeaders.DELIVERY_TAG)), false);
//        System.out.println("msg = " + msg);
    }

    /**
     * concurrency 指的是并发数量，即这个消费者将开启 20 个子线程去消费消息
     *
     * @param msg
     */
    @RabbitListener(queues = RabbitConfig.QUEUE_NAME, concurrency = "20")
    public void handleMsg2(Message message,Channel channel) throws IOException {
//        System.out.println("msg2 = " + msg + "--->" + Thread.currentThread().getName());
        //拒绝消费该消息，requeue 表示被拒绝的消息是否重新进入队列中
        channel.basicReject((Long) message.getHeaders().get(AmqpHeaders.DELIVERY_TAG),true);
    }
}
