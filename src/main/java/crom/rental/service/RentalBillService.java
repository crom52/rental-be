package crom.rental.service;

import static crom.rental.common.ExceptionMessage.WRONG_BILL_ID;
import crom.rental.entity.Bill;
import crom.rental.repository.IMongoBillRepository;
import crom.rental.repository.IRentalBillRepository;
import static java.lang.Integer.parseInt;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class RentalBillService {
  private final IRentalBillRepository jpaRepo;
  private final IMongoBillRepository mongoRepo;
  private final RabbitMQProducer rabbitSender;


  @Transactional
  public Bill saveBill(Bill bill) {
    validateIdMatchWithPeriodAndRoomNo(bill);
    Bill savaData = bill.setBaseEntity(bill);
    mongoRepo.save(savaData);
    jpaRepo.save(savaData);
    saveOldInfoForNextPeriod(bill);
    rabbitSender.publish(bill);
    return bill;
  }


  private void validateIdMatchWithPeriodAndRoomNo(Bill bill) {
    String expectBillId = bill.getRoomNumber() + bill.getRentalPeriod();
    if (!bill.getId().equals(expectBillId)) {
      throw new IllegalArgumentException(WRONG_BILL_ID);
    }
  }

  public void saveOldInfoForNextPeriod(Bill currentBill) {
    Bill nextPeriodBill = new Bill();
    String[] currentPeriodId = currentBill.getId().split("-");

    String roomNo = currentPeriodId[0];
    String month = currentPeriodId[1];
    String year = currentPeriodId[2];
    String newPeriod;
    if (month.equals("12")) {
      newPeriod = "01-" + (parseInt(year) + 1);
      nextPeriodBill.setRentalPeriod(newPeriod);
    } else {
      newPeriod = parseInt(month) + 1 + "-" + year;
      nextPeriodBill.setRentalPeriod(parseInt(month) + 1 + "-" + year);
    }
    nextPeriodBill.setId(roomNo + "-" + newPeriod);
    nextPeriodBill.setOldElecNumber(currentBill.getNewElecNumber());
    nextPeriodBill.setOldWaterNumber(currentBill.getNewWaterNumber());
    nextPeriodBill.setBaseEntity(nextPeriodBill);
    jpaRepo.save(nextPeriodBill);
  }

}
