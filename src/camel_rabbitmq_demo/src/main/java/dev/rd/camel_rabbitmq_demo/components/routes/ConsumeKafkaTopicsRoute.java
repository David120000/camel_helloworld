package dev.rd.camel_rabbitmq_demo.components.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import dev.rd.camel_rabbitmq_demo.components.enricher.KafkaHelloEnricher;

@Component
public class ConsumeKafkaTopicsRoute extends RouteBuilder{

    private final String KAFKA_ENDPOINT;

    public ConsumeKafkaTopicsRoute() {
        this.KAFKA_ENDPOINT = "kafka:%s?brokers=localhost:29092";
    }


    @Override
    public void configure() throws Exception {

        fromF(KAFKA_ENDPOINT, "hello-kafka")
            .log("[${header.kafka.OFFSET}] [${body}]")
            .bean(KafkaHelloEnricher.class)
            .toF(KAFKA_ENDPOINT, "log-greetings");
    }
    

}
