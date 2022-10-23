package crom.rental.service.bill;

import com.google.gson.Gson;
import crom.rental.entity.Bill;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BillProducer {
    private final RabbitTemplate rabbitTemplate;
    private final TopicExchange exchange;


    @Autowired
    @Value("${rental.rabbitmq.routingkey}")
    private String routingKey;

    public void publish(Bill message) {
        String jsonMessage = new Gson().toJson(message);
        rabbitTemplate.convertAndSend(exchange.getName(), routingKey, jsonMessage);
        System.out.println("Publish successfully " + jsonMessage);
    }
}
