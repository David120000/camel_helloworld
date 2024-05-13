package dev.rd.camel_rabbitmq_demo.components.enricher;

import java.time.LocalDateTime;

public class KafkaHelloEnricher {

    public String enrichWithDate(String greetingText) {
        return LocalDateTime.now() + " :: " + greetingText;
    }
    
}
