package org.javaboy.manual.receiver;

import com.rabbitmq.client.Channel;
import org.javaboy.manual.config.RabbitConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Configuration;

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
//@Configuration
public class MsgReceiver {

    private static final Logger logger = LoggerFactory.getLogger(MsgReceiver.class);

    @RabbitListener(queues = RabbitConfig.JAVABOY_QUEUE_NAME)
    public void handleMsg(Message message, Channel channel) {
        //获取消息的一个标记
        long deliveryTag = message.getMessageProperties().getDeliveryTag();
        try {
            //开始消息的消费
            byte[] body = message.getBody();
            String s = new String(body);
            logger.info("handleMsg,{}", s);
            int i = 1 / 0;
            //消费完成后，手动ack
            //第一个参数是消息的标记，第二个参数如果为 false，表示仅仅确认当前消息，如果为 true，表示之前所有的消息都确认消费成功
            channel.basicAck(deliveryTag,false);
        } catch (Exception e) {
            //手动 nack，告诉 mq 这条消息消费失败
            try {
                channel.basicNack(deliveryTag,false,true);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
