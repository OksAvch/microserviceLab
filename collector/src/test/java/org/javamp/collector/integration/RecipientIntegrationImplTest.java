package org.javamp.collector.integration;

import io.micrometer.core.instrument.Counter;
import org.javamp.collector.service.MessageService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RecipientIntegrationImplTest {

    public static final String URL = "url";
    public static final String MESSAGE = "\"message1\"";
    @Mock
    RestTemplate restTemplateMock;
    @Mock
    MessageService messageService;
    @Mock
    Counter meterRegistry;

    RecipientIntegrationImpl sut;

    @Test
    void shouldCountZeroMessages() {
        sut = new RecipientIntegrationImpl(URL, restTemplateMock, meterRegistry, messageService);

        when(restTemplateMock.getForObject(URL, String.class)).thenReturn(MESSAGE);

        assertEquals(MESSAGE, sut.pullMessage());
    }

    @Test
    void shouldCountMessages() {
        sut = new RecipientIntegrationImpl(URL, restTemplateMock, meterRegistry, messageService);

        when(restTemplateMock.getForObject(URL, String.class)).thenReturn(null);

        assertEquals(null, sut.pullMessage());
    }
}