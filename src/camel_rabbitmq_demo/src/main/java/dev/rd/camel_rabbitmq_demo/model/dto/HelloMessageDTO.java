package dev.rd.camel_rabbitmq_demo.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class HelloMessageDTO {

    private String sender;
    private String text;
    private String received;
    
}
