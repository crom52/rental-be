package crom.rental.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.tools.json.JSONWriter;
import crom.rental.entity.Bill;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.concurrent.TimeoutException;

@Service
@RequiredArgsConstructor
public class RabbitMQProducer {
    private final RabbitTemplate rabbitTemplate;

    private final TopicExchange exchange;

    @Autowired
    @Value("${rental.rabbitmq.routingkey}")
    private String routingKey;

    public void publish(Bill message) {
        Gson gson = new Gson();
        message.setElecPrice(1D);
        message.setWaterPrice(1D);
        message.setNewElecNumber(1);
        message.setNewWaterNumber(1);
        message.setOldElecNumber(1);
        message.setNewWaterNumber(1);
        message.setId("1");
        message.setOtherPrice(1D);
        message.setBaseEntity(message);
        message.setRentalPeriod("1");
        message.setRoomNumber("1");
        message.setTotalElecMoney(1D);
        message.setTotalMoney(1D);
        message.setTotalElecMoney(1D);
        message.setRentalPrice(1D);


        rabbitTemplate.convertAndSend(exchange.getName(), routingKey, gson.toJson(message));
//        System.out.println("Publish successfully " + jsonMessage);
        System.out.println("publish ok");
    }
}
