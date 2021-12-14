package org.javaboy.publisher;

import org.javaboy.publisher.config.RabbitConfig;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PublisherApplicationTests {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Test
    void contextLoads() {
        for (int i = 0; i < 20; i++) {
            rabbitTemplate.convertAndSend(RabbitConfig.QUEUE_NAME, "hello 江南一点雨:" + i);
        }
    }

}
