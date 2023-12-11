package org.javamp.collector.configuration;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.javamp.collector.enums.PrometheusCounter;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class SpringConfiguration {

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Bean
    public Counter counter(MeterRegistry meterRegistry) {
        return Counter
                .builder(PrometheusCounter.MESSAGES_SENT.getName())
                .description(PrometheusCounter.MESSAGES_SENT.getDescription())
                .register(meterRegistry);
    }
}
