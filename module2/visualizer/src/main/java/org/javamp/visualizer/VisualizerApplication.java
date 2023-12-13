package org.javamp.visualizer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;


@SpringBootApplication
@PropertySource("classpath:application.yml")
public class VisualizerApplication {

    public static void main(String[] args) {
        SpringApplication.run(VisualizerApplication.class, args);
    }

}
