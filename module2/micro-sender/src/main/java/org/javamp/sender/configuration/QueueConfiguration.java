package org.javamp.sender.configuration;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QueueConfiguration {

    public static final String OUTBOUND_QUEUE = "rabbitmq.notifications";
    public static final String OUTBOUND_EXCHANGE = "rabbitmq-exchange";
    public static final String OUTBOUND_ROUTING_KEY = "rabbitmq.notification.key.#";

    @Bean
    public Queue outboundQueue() {
        return new Queue(OUTBOUND_QUEUE, false);
    }

    @Bean
    TopicExchange exchange() {
        return new TopicExchange(OUTBOUND_EXCHANGE);
    }

    @Bean
    Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(OUTBOUND_ROUTING_KEY);
    }
}
