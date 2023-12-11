package org.javamp.sender.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PrometheusCounter {
    MESSAGES_SENT("messages_sent", "Number of messages sent by sender to the queue");

    private final String name;
    private final String description;
}
