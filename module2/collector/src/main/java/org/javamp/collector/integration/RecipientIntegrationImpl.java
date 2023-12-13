package org.javamp.collector.integration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.micrometer.core.instrument.Counter;
import lombok.extern.slf4j.Slf4j;
import org.javamp.collector.service.MessageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
    public List<String> pullMessages() {
        String response = restTemplate.getForObject(recipientUrl, String.class);

        log.info("response: '{}'", response);
        List<String> receivedMessages = parseMessages(response);

        log.info("messages got: {}", receivedMessages.size());
        if (!receivedMessages.isEmpty()) {
            log.info("messages counter before increment: {}", messagesCounter.count());
            messageService.saveMessage(receivedMessages);
            messagesCounter.increment(receivedMessages.size());
        }

        log.info("messages counter in the end: {}", messagesCounter.count());
        return receivedMessages;
    }

    @SuppressWarnings("unchecked")
    private List<String> parseMessages(String response) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return (ArrayList<String>) objectMapper.readValue(response, ArrayList.class);
        } catch (JsonProcessingException e) {
            log.error("Messages list could not be parsed: {}", response);
            return Collections.emptyList();
        }
    }
}
