package org.javaboy.manual;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.GetResponse;
import org.javaboy.manual.config.RabbitConfig;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
class ManualApplicationTests {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Test
    void contextLoads() {
        long deliveryTag = 0;
        Channel channel = rabbitTemplate.getConnectionFactory().createConnection().createChannel(false);
        try {
            GetResponse getResponse = channel.basicGet(RabbitConfig.JAVABOY_QUEUE_NAME, false);
            deliveryTag = getResponse.getEnvelope().getDeliveryTag();
			String s = new String(getResponse.getBody());
			System.out.println("s = " + s);
			int i = 1 / 0;
			channel.basicAck(deliveryTag, false);
		} catch (IOException e) {
			try {
				channel.basicNack(deliveryTag, false, true);
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
    }

}
