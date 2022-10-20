package crom.rental.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import crom.rental.entity.Bill;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;

@Slf4j
@Service
@RequiredArgsConstructor
public class RabbitMQConsumer {

//    @SneakyThrows
//    @Override
//    public void onMessage(Message message) {
//        System.out.println("listen ok ");
//        System.out.println(prepareNextPeriodData(message).get());
////      prepareNextPeriodData(message).ifPresent(rentalBillService::saveOldInfoForNextPeriod);
//    }

    @RabbitListener(queues = "save-bill-queue")
    public void consumeJsonMessage(Message bill) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
//        mapper.disable(DeserializationFeature.FAIL_ON_MISSING_CREATOR_PROPERTIES);
        byte[] body = bill.getBody();
        String s = new String(bill.getBody(), StandardCharsets.UTF_8);
        s = s.substring(1,s.lastIndexOf("\""));
        String test2 = "{\"id\":\"1\",\"oldElecNumber\":1,\"newElecNumber\":1,\"elecPrice\":1.0,\"totalElecMoney\":1.0,\"newWaterNumber\":1,\"waterPrice\":1.0,\"rentalPrice\":1.0,\"otherPrice\":1.0,\"totalMoney\":1.0,\"rentalPeriod\":\"1\",\"roomNumber\":\"1\",\"createdTime\":\"Oct 21, 2022, 12:54:45 AM\",\"updatedTime\":\"Oct 21, 2022, 12:54:45 AM\",\"createdBy\":\"SYSTEM\",\"updatedBy\":\"SYSTEM\"}";
        mapper.readValue(test2, Bill.class);
        Gson gson = new Gson();
        gson.fromJson(s,Bill.class);
//        String str = map.get(bill).toString();

//        var x = mapper.readValue(String.valueOf(bill), Bill[].class);

//        System.out.println(bill.getBody());
//        System.out.println(String.format("Received JSON message -> %s", bill.toString()));
    }

//
//    private Optional<Bill> prepareNextPeriodData(Message message)  {
////        return Optional
////                .of(message)
////                .filter(ObjectUtils::isNotEmpty)
////                .map(el -> deserialize(el.getBody()))
////                .map(String::valueOf)
////                .map(el -> gson.fromJson(el, Bill.class));
//    }

}
