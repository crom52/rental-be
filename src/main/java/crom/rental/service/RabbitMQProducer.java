package crom.rental.service;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RabbitMQProducer {
  private final AmqpTemplate amqpTemplate;

  public void publish(Object message) {
    amqpTemplate.convertAndSend(message);
  }
}
