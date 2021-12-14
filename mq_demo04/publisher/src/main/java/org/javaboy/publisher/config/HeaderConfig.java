package org.javaboy.publisher.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.HeadersExchange;
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
 * 交换机根据消息的头信息来决定消息去哪一个队列
 */
@Configuration
public class HeaderConfig {
    public static final String HEADER_QUEUE_NAME_NAME = "header_queue_name_name";
    public static final String HEADER_QUEUE_AGE_NAME = "header_queue_age_name";
    public static final String HEADER_EXCHANGE_NAME = "header_exchange_name";

    @Bean
    Queue headerNameQueue() {
        return new Queue(HEADER_QUEUE_NAME_NAME, true, false,false);
    }
    @Bean
    Queue headerAgeQueue() {
        return new Queue(HEADER_QUEUE_AGE_NAME, true, false,false);
    }

    @Bean
    HeadersExchange headersExchange() {
        return new HeadersExchange(HEADER_EXCHANGE_NAME, true, false);
    }

    @Bean
    Binding nameBinding() {
        return BindingBuilder.bind(headerNameQueue())
                .to(headersExchange())
                //如果将来消息头部中包含 name 属性，就算匹配成功
                .where("name").exists();
    }

    @Bean
    Binding ageBinding() {
        return BindingBuilder.bind(headerAgeQueue())
                .to(headersExchange())
                //将来头信息中必须要有 age 属性，并且 age 属性值为 99
                .where("age")
                .matches(99);
    }
}
