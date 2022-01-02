package org.javaboy.message_delay_plugin.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.CustomExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 江南一点雨
 * @微信公众号 江南一点雨
 * @网站 http://www.itboyhub.com
 * @国际站 http://www.javaboy.org
 * @微信 a_java_boy
 * @GitHub https://github.com/lenve
 * @Gitee https://gitee.com/lenve
 */
@Configuration
public class RabbitConfig {
    public static final String DELAY_MSG_QUEUE_NAME = "delay_msg_queue_name";
    public static final String DELAY_MSG_EXCHANGE_NAME = "delay_msg_exchange_name";

    @Bean
    Queue delayQueue() {
        return new Queue(DELAY_MSG_QUEUE_NAME, true, false, false);
    }

    @Bean
    CustomExchange customExchange() {
        Map<String, Object> args = new HashMap<>();
        //指定交换机的类型
        args.put("x-delayed-type", "direct");
        return new CustomExchange(DELAY_MSG_EXCHANGE_NAME, "x-delayed-message", true, false, args);
    }

    @Bean
    Binding delayedBinding() {
        return BindingBuilder.bind(delayQueue())
                .to(customExchange())
                .with(DELAY_MSG_QUEUE_NAME)
                .noargs();
    }

}
