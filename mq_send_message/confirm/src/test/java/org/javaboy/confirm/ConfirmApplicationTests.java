package org.javaboy.confirm;

import org.javaboy.confirm.config.RabbitConfig;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ConfirmApplicationTests {

	@Autowired
	RabbitTemplate rabbitTemplate;
	@Test
	void contextLoads() {
		//拉模式
		Object o = rabbitTemplate.receiveAndConvert(RabbitConfig.JAVABOY_QUEUE_NAME);
		System.out.println("o = " + o);
	}

}
