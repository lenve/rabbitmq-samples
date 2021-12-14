package org.javaboy.publisher.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
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
public class TopicConfig {
    public static final String XIAOMI_QUEUE_NAME = "xiaomi_queue_name";
    public static final String HUAWEI_QUEUE_NAME = "huawei_queue_name";
    public static final String PHONE_QUEUE_NAME = "phone_queue_name";
    public static final String TOPIC_EXCHANGE_NAME = "topic_queue_name";

    @Bean
    Queue xiaomiQueue() {
        return new Queue(XIAOMI_QUEUE_NAME, true, false, false);
    }
    @Bean
    Queue huaweiQueue() {
        return new Queue(HUAWEI_QUEUE_NAME, true, false, false);
    }
    @Bean
    Queue phoneQueue() {
        return new Queue(PHONE_QUEUE_NAME, true, false, false);
    }

    @Bean
    TopicExchange topicExchange() {
        return new TopicExchange(TOPIC_EXCHANGE_NAME, true, false);
    }

    @Bean
    Binding xiaomiBinding() {
        return BindingBuilder.bind(xiaomiQueue())
                .to(topicExchange())
                //这里的 # 是一个通配符，表示将来消息的 routing_key 只要是以 xiaomi 开头，都将被路由到 xiaomiQueue
                .with("xiaomi.#");
    }
    @Bean
    Binding huaweiBinding() {
        return BindingBuilder.bind(huaweiQueue())
                .to(topicExchange())
                //这里的 # 是一个通配符，表示将来消息的 routing_key 只要是以 xiaomi 开头，都将被路由到 xiaomiQueue
                .with("huawei.#");
    }
    @Bean
    Binding phoneBinding() {
        return BindingBuilder.bind(phoneQueue())
                .to(topicExchange())
                //这里的 # 是一个通配符，表示将来消息的 routing_key 只要是以 xiaomi 开头，都将被路由到 xiaomiQueue
                .with("#.phone.#");
    }
}
