package crom.rental.service;

import com.google.gson.Gson;
import crom.rental.entity.Bill;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.stereotype.Service;
import static org.springframework.util.SerializationUtils.deserialize;

@Slf4j
@Service
@RequiredArgsConstructor
public class RabbitMQConsumer implements MessageListener {
  private final RentalBillService rentalBillService;

  @Override
  public void onMessage(Message message) {
      System.out.println("listen ok ");
      prepareNextPeriodData(message).ifPresent(rentalBillService::saveOldInfoForNextPeriod);
  }

  private Optional<Bill> prepareNextPeriodData(Message message) {
    Gson gson = new Gson();
    return Optional
        .of(message)
        .filter(ObjectUtils::isNotEmpty)
        .map(el -> deserialize(el.getBody()))
        .map(String::valueOf)
        .map(el -> gson.fromJson(el, Bill.class));
  }
}
