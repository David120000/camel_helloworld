package dev.rd.camel_rabbitmq_demo.components.routes;

import java.time.LocalDateTime;
import java.util.Random;

import org.apache.camel.Message;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import dev.rd.camel_rabbitmq_demo.components.aggregation.TimestampAggregationStrategy;

@Component
public class AggregatorFromTimer extends RouteBuilder {

    private final String CORRELATION_ID;

    public AggregatorFromTimer() {
        this.CORRELATION_ID = "correlationId";
    }


    @Override
    public void configure() throws Exception {

        Random random = new Random();
        
        // from("timer:timestamps?period=200")
        //     .process(exchange -> {
        //         Message message = exchange.getMessage();
        //         message.setHeader(CORRELATION_ID, random.nextInt(4));
        //         message.setBody(LocalDateTime.now().toString());
        //     })
        //     .aggregate(header(CORRELATION_ID), new TimestampAggregationStrategy())
        //     .completionSize(4)
        //     .log("${header." + CORRELATION_ID + "} - ${body}");
    }
    

}
