package org.javamp.collector.integration;

import io.micrometer.core.instrument.Counter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Slf4j
@Component
public class RecipientIntegrationImpl implements RecipientIntegration {
    public static final String SPLITERATOR = ",";
    public static final String START_CHARACTER = "[";
    public static final String END_CHARACTER = "]";
    public static final String EMPTY = "";

    private final String recipientUrl;
    private final RestTemplate restTemplate;
    private final Counter messagesCounter;

    public RecipientIntegrationImpl(
            @Value("${integration.recipient.url}") String recipientUrl,
            RestTemplate restTemplate,
            Counter messagesCounter) {

        this.recipientUrl = recipientUrl;
        this.restTemplate = restTemplate;
        this.messagesCounter = messagesCounter;
    }

    @Override
    public List<String> pullMessages() {
        String response = restTemplate.getForObject(recipientUrl, String.class);

        log.info("response: '{}'", response);
        List<String> receivedMessages = parseMessages(response);

        log.info("messages got: {}", receivedMessages.size());
        if (!receivedMessages.isEmpty()) {
            log.info("messages counter before increment: {}", messagesCounter.count());
            messagesCounter.increment(receivedMessages.size());
        }

        log.info("messages counter in the end: {}", messagesCounter.count());
        return receivedMessages;
    }

    private List<String> parseMessages(String response) {
        return Optional.ofNullable(response)
                .map(r -> r.replace(START_CHARACTER, EMPTY))
                .map(r -> r.replace(END_CHARACTER, EMPTY))
                .filter(s -> !s.isEmpty())
                .map(r -> Arrays.asList(r.split(SPLITERATOR)))
                .orElse(Collections.emptyList());
    }
}
