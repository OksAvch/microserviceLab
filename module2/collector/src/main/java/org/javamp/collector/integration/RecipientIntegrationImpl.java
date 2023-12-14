package org.javamp.collector.integration;

import io.micrometer.core.instrument.Counter;
import lombok.extern.slf4j.Slf4j;
import org.javamp.collector.service.MessageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@Slf4j
@Component
public class RecipientIntegrationImpl implements RecipientIntegration {

    private final String recipientUrl;
    private final RestTemplate restTemplate;
    private final Counter messagesCounter;
    private final MessageService messageService;

    public RecipientIntegrationImpl(
            @Value("${integration.recipient.url}") String recipientUrl,
            RestTemplate restTemplate,
            Counter messagesCounter,
            MessageService messageService) {

        this.recipientUrl = recipientUrl;
        this.restTemplate = restTemplate;
        this.messagesCounter = messagesCounter;
        this.messageService = messageService;
    }

    @Override
    public String pullMessage() {
        String response = restTemplate.getForObject(recipientUrl, String.class);

        log.info("response: '{}'", response);
        String receivedMessage = parseMessages(response);

        log.info("messages got: {}", receivedMessage);
        if (Objects.isNull(receivedMessage)) {
            return null;
        }

        log.info("messages counter before increment: {}", messagesCounter.count());
        messageService.saveMessage(receivedMessage);
        messagesCounter.increment();

        log.info("messages counter in the end: {}", messagesCounter.count());
        return receivedMessage;
    }

    private String parseMessages(String response) {
        return response;
    }
}
