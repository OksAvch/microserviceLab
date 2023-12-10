package org.javamp.recipient.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.javamp.recipient.data.MessageStorage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class MessageServiceImpl implements MessageService {

    private final MessageStorage storage;
    private final RabbitTemplate rabbitTemplate;

    @Override
    public List<String> getMessages() {
        return storage.pull();
    }

    @Override
    public void pulMessages(String queue) {
        Optional.ofNullable(rabbitTemplate.receiveAndConvert(queue))
                .ifPresent(m -> storeMessage(m.toString()));
    }

    private void storeMessage(String message) {
        log.info("Message was stored: {}", message);
        storage.store(message);
    }
}
