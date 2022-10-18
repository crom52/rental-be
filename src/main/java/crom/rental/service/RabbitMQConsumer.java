package crom.rental.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

@Slf4j
public class RabbitMQConsumer {
  @RabbitListener(queues = "${rental.rabbitmq.queue}")
  public void consume(Object message){
    log.info("Received message {}", message);
  }
}
