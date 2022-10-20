package crom.rental.service;

import com.google.gson.Gson;
import crom.rental.entity.Bill;
import crom.rental.repository.IMongoBillRepository;
import crom.rental.repository.IRentalBillRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeoutException;

import static crom.rental.common.ExceptionMessage.WRONG_BILL_ID;
import static org.springframework.util.SerializationUtils.deserialize;

@Service
@Slf4j
@RequiredArgsConstructor
@RabbitListener(queues = "save-bill-queue")
public class RentalBillService implements MessageListener {
  private final IRentalBillRepository jpaRepo;
  private final IMongoBillRepository mongoRepo;
  private final RabbitMQProducer rabbitSender;



  @Transactional
  public Bill saveBill(Bill bill) throws IOException, TimeoutException {
//    validateIdMatchWithPeriodAndRoomNo(bill);
    Bill savaData = bill.setBaseEntity(bill);
    mongoRepo.save(savaData);
    jpaRepo.save(savaData);
//    saveOldInfoForNextPeriod(bill);
    rabbitSender.publish(bill);
    return bill;
  }


  private void validateIdMatchWithPeriodAndRoomNo(Bill bill) {
    String expectBillId = bill.getRoomNumber() + bill.getRentalPeriod();
    if (!bill.getId().equals(expectBillId)) {
      throw new IllegalArgumentException(WRONG_BILL_ID);
    }
  }

//  @RabbitHandler
//  @RabbitListener(queues = "save-bill-queue")
//  public void saveOldInfoForNextPeriod(Bill currentBill) {
//    System.out.println("xxxx");
////    Bill nextPeriodBill = new Bill();
////    String[] currentPeriodId = currentBill.getId().split("-");
////
////    String roomNo = currentPeriodId[0];
////    String month = currentPeriodId[1];
////    String year = currentPeriodId[2];
////    String newPeriod;
////    if (month.equals("12")) {
////      newPeriod = "01-" + (parseInt(year) + 1);
////      nextPeriodBill.setRentalPeriod(newPeriod);
////    } else {
////      newPeriod = parseInt(month) + 1 + "-" + year;
////      nextPeriodBill.setRentalPeriod(parseInt(month) + 1 + "-" + year);
////    }
////    nextPeriodBill.setId(roomNo + "-" + newPeriod);
////    nextPeriodBill.setOldElecNumber(currentBill.getNewElecNumber());
////    nextPeriodBill.setOldWaterNumber(currentBill.getNewWaterNumber());
////    nextPeriodBill.setBaseEntity(nextPeriodBill);
////    jpaRepo.save(nextPeriodBill);
//  }

  public Bill findBillInfo(String roomNumber, String rentalPeriod) {
    String id = roomNumber + "-" + rentalPeriod;
    return jpaRepo.findById(id).orElse(null);
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


  public List<Bill> findBillByPeriod(String rentalPeriod) {
    return null;
  }

  @Override
  public void onMessage(Message message) {
    System.out.println("ok222");
  }
}
