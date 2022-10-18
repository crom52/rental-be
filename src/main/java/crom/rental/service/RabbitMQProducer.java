package crom.rental.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RabbitMQProducer {
    private final RabbitTemplate rabbitTemplate;

    private final DirectExchange directExchange;

    @Autowired
    @Value("${rental.rabbitmq.routingkey}")
    private String routingKey;

    public void publish(Object message) {
        Gson json = new Gson();
        String jsonMessage = json.toJson(message);
        rabbitTemplate.convertAndSend(directExchange.getName(), routingKey, jsonMessage);
        System.out.println("Publish successfully " + jsonMessage);
    }
}
