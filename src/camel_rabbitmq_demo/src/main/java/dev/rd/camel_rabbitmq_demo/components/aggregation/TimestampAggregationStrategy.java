package dev.rd.camel_rabbitmq_demo.components.aggregation;

import org.apache.camel.AggregationStrategy;
import org.apache.camel.Exchange;

public class TimestampAggregationStrategy implements AggregationStrategy {

    @Override
    public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
        
        Exchange aggregatedExchange = newExchange;

        if(oldExchange != null) {

            String oldBody = oldExchange.getIn().getBody(String.class);
            String newBody = newExchange.getIn().getBody(String.class);

            String aggregatedBody = oldBody + " :: " + newBody;

            aggregatedExchange.getIn().setBody(aggregatedBody);
        }

        return aggregatedExchange;
    }

}
