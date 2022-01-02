package org.javaboy.queues_ttl.config;

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
    public static final String JAVABOY_QUEUES_DELAY_QUEUE_NAME = "javaboy_queues_delay_queue_name";
    public static final String JAVABOY_QUEUES_DELAY_EXCHANGE_NAME = "javaboy_queues_delay_exchange_name";

    @Bean
    Queue messageDelayQueue() {
        Map<String, Object> args = new HashMap<>();
        //给消息队列设置过期时间，该队列中的消息如果10s之内没人消费，则过期
        args.put("x-message-ttl", 10000);
        return new Queue(JAVABOY_QUEUES_DELAY_QUEUE_NAME, true, false, false,args);
    }

    @Bean
    DirectExchange messageDelayExchange() {
        return new DirectExchange(JAVABOY_QUEUES_DELAY_EXCHANGE_NAME, true, false);
    }

    @Bean
    Binding messageDelayQueueBinding() {
        return BindingBuilder.bind(messageDelayQueue())
                .to(messageDelayExchange())
                .with(JAVABOY_QUEUES_DELAY_QUEUE_NAME);
    }
}
