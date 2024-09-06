package org.javamp.recipient.configuration;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.javamp.recipient.service.MessageService;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Slf4j
@Configuration
@EnableScheduling
@AllArgsConstructor
public class ScheduleConfiguration {
    private static final String INBOUND_QUEUE = "rabbitmq.notifications";

    private final MessageService messageService;

    @Scheduled(fixedDelay = 4000)
    public void scheduleFixedDelayTask() {
        log.info("Initiating message pull");

        messageService.pulMessages(INBOUND_QUEUE);

        log.info("Message pull completed");
    }
}
