package crom.rental.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import crom.rental.configuration.RabbitMQConfig;
import crom.rental.entity.Bill;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import org.springframework.util.SerializationUtils;

import java.io.ByteArrayInputStream;
import java.io.DataInput;
import java.io.ObjectInputStream;
import java.util.Arrays;
import java.util.Map;

@Slf4j
@Service
public class RabbitMQConsumer implements MessageListener {
    @Override
    public void onMessage(Message message) {
        System.out.println("listen ok " );
    }
}
