package org.javaboy.consumer.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
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

/**
 * fanout 交换机会将到达交换机的所有消息路由到与他绑定的所有队列上面来
 */
@Configuration
public class FanoutConfig {
    public static final String FANOUT_QUEUE_NAME = "fanout_queue_name";
    public static final String FANOUT_QUEUE_NAME2 = "fanout_queue_name2";
    public static final String FANOUT_EXCHANGE_NAME = "fanout_exchange_name";

    @Bean
    Queue fanoutQueue1() {
        return new Queue(FANOUT_QUEUE_NAME, true, false, false);
    }
    @Bean
    Queue fanoutQueue2() {
        return new Queue(FANOUT_QUEUE_NAME2, true, false, false);
    }

    @Bean
    FanoutExchange fanoutExchange() {
        return new FanoutExchange(FANOUT_EXCHANGE_NAME, true, false);
    }

    @Bean
    Binding fanoutBinding1() {
        return BindingBuilder.bind(fanoutQueue1())
                .to(fanoutExchange());
    }
    @Bean
    Binding fanoutBinding2() {
        return BindingBuilder.bind(fanoutQueue2())
                .to(fanoutExchange());
    }
}
