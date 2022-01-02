package org.javaboy.message_delay_dlx.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
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
    public static final String JAVABOY_QUEUE_NAME = "javaboy_queue_name";
    public static final String JAVABOY_EXCHANGE_NAME = "javaboy_exchange_name";
    public static final String DELAY_EXCHANGE_NAME = "delay_exchange_name";
    public static final String DELAY_QUEUE_NAME = "delay_queue_name";

    @Bean
    Binding msgBinding() {
        return BindingBuilder.bind(msgQueue())
                .to(msgExchange())
                .with(JAVABOY_QUEUE_NAME);
    }

    @Bean
    DirectExchange msgExchange() {
        return new DirectExchange(JAVABOY_EXCHANGE_NAME, true, false);
    }

    @Bean
    Queue msgQueue() {
        Map<String, Object> args = new HashMap<>();
        args.put("x-message-ttl", 10000);
        args.put("x-dead-letter-exchange", DELAY_EXCHANGE_NAME);
        args.put("x-dead-letter-routing-key", DELAY_QUEUE_NAME);
        return new Queue(JAVABOY_QUEUE_NAME, true, false, false, args);
    }

    @Bean
    Binding dlxBinding() {
        return BindingBuilder.bind(dlxQueue())
                .to(dlxExchange())
                .with(DELAY_QUEUE_NAME);
    }

    @Bean
    DirectExchange dlxExchange() {
        return new DirectExchange(DELAY_EXCHANGE_NAME, true, false);
    }

    @Bean
    Queue dlxQueue() {
        return new Queue(DELAY_QUEUE_NAME, true, false, false);
    }

}
