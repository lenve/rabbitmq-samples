package org.javaboy.message_ttl.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
    public static final String JAVABOY_MESSSAGE_DELAY_QUEUE_NAME = "javaboy_messsage_delay_queue_name";
    public static final String JAVABOY_MESSSAGE_DELAY_EXCHANGE_NAME = "javaboy_messsage_delay_exchange_name";

    @Bean
    Queue messageDelayQueue() {
        return new Queue(JAVABOY_MESSSAGE_DELAY_QUEUE_NAME, true, false, false);
    }

    @Bean
    DirectExchange messageDelayExchange() {
        return new DirectExchange(JAVABOY_MESSSAGE_DELAY_EXCHANGE_NAME, true, false);
    }

    @Bean
    Binding messageDelayQueueBinding() {
        return BindingBuilder.bind(messageDelayQueue())
                .to(messageDelayExchange())
                .with(JAVABOY_MESSSAGE_DELAY_QUEUE_NAME);
    }
}
