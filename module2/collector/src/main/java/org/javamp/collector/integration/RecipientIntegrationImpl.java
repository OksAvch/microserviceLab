package org.javamp.collector.integration;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class RecipientIntegrationImpl implements RecipientIntegration {
    public static final String RECIPIENT_URL = "http://localhost:8091/messages";
    public static final String SPLITERATOR = ", ";

    private final RestTemplate restTemplate;

    @Override
    public List<String> pullMessages() {
        String response = restTemplate.getForObject(RECIPIENT_URL, String.class);

        return Optional.ofNullable(response)
                .map(r -> Arrays.asList(r.split(SPLITERATOR)))
                .orElse(Collections.emptyList());
    }
}
