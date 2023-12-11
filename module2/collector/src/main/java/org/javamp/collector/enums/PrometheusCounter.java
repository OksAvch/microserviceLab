package org.javamp.collector.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PrometheusCounter {
    MESSAGES_SENT("collector_messages_counter", "General number of messages collected");

    private final String name;
    private final String description;
}
