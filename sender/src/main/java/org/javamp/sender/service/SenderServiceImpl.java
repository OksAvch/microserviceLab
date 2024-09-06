package org.javamp.sender.service;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.extern.slf4j.Slf4j;
import org.javamp.sender.configuration.QueueConfiguration;
import org.javamp.sender.dto.MessageDto;
import org.javamp.sender.enums.PrometheusCounter;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SenderServiceImpl implements SenderService {

    private final RabbitTemplate rabbitTemplate;
    private final Counter messegesSentCounter;

    public SenderServiceImpl(RabbitTemplate rabbitTemplate, MeterRegistry meterRegistry) {
        this.rabbitTemplate = rabbitTemplate;
        this.messegesSentCounter = Counter
                .builder(PrometheusCounter.MESSAGES_SENT.getName())
                .description(PrometheusCounter.MESSAGES_SENT.getDescription())
                .register(meterRegistry);
    }

    @Override
    public void sendNotification(MessageDto message) {
        log.info("Sending message: {}", message);

        rabbitTemplate.convertAndSend(
                QueueConfiguration.OUTBOUND_EXCHANGE,
                QueueConfiguration.OUTBOUND_ROUTING_KEY,
                message.getMessage());

        messegesSentCounter.increment();
    }
}
