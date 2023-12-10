package org.javamp.collector.integration;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class RecipientIntegrationImpl implements RecipientIntegration {
    public static final String SPLITERATOR = ", ";

    @Value("${integration.recipient.url}")
    private String recipientUrl;
    private final RestTemplate restTemplate;

    @Override
    public List<String> pullMessages() {
        String response = restTemplate.getForObject(recipientUrl, String.class);

        return Optional.ofNullable(response)
                .map(r -> Arrays.asList(r.split(SPLITERATOR)))
                .orElse(Collections.emptyList());
    }
}
