package dev.rd.camel_rabbitmq_demo.components.routes;

import java.time.LocalDateTime;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.apache.camel.support.DefaultMessage;
import org.springframework.stereotype.Component;

import dev.rd.camel_rabbitmq_demo.model.dto.HelloMessageDTO;

@Component
public class ConsumeRabbitRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        
        from("spring-rabbitmq:amq.direct?queues=hello&routingKey=hello")
            .log("Original message: ${body}")
            .unmarshal().json(JsonLibrary.Jackson, HelloMessageDTO.class)
            .process(this::processHelloDTO)
            .log("After processing: ${body}")
            .marshal().json(JsonLibrary.Jackson, HelloMessageDTO.class)
            .wireTap("spring-rabbitmq:amq.direct?routingKey=processed&queues=processed")
            // .to("spring-rabbitmq:amq.direct?routingKey=processed&queues=processed")
            .to("file://files/?fileName=processed-events.txt&fileExist=Append");
    }


    private void processHelloDTO(Exchange exchange) {
        
        var dto = exchange.getMessage().getBody(HelloMessageDTO.class);
        dto.setReceived(LocalDateTime.now().toString());

        Message output = new DefaultMessage(exchange);
        output.setBody(dto);
        exchange.setMessage(output);
    }
    

}
