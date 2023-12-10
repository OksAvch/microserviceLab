package org.javamp.sender.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.javamp.sender.configuration.QueueConfiguration;
import org.javamp.sender.dto.MessageDto;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class SenderServiceImpl implements SenderService {
    private final RabbitTemplate rabbitTemplate;

    @Override
    public void sendNotification(MessageDto message) {
        log.info("Sending message: {}", message);

        rabbitTemplate.convertAndSend(
                QueueConfiguration.OUTBOUND_EXCHANGE,
                QueueConfiguration.OUTBOUND_ROUTING_KEY,
                message.getMessage());
    }
}
