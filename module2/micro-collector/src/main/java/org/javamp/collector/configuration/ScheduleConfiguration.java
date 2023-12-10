package org.javamp.collector.configuration;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.javamp.collector.integration.RecipientIntegration;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;

@Slf4j
@Configuration
@EnableScheduling
@AllArgsConstructor
public class ScheduleConfiguration {
    private final RecipientIntegration recipientIntegration;

    @Scheduled(fixedDelay = 10000)
    public void scheduleFixedDelayTask() {
        log.info("Initiating message pull");

        List<String> messagesReceived = recipientIntegration.pullMessages();

        log.info("Messages received: {}", messagesReceived);
    }
}
