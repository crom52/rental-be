package crom.rental.bill.service;

import static crom.rental.common.ExceptionMessage.WRONG_BILL_ID;
import crom.rental.bill.entity.Bill;
import org.springframework.stereotype.Service;

@Service
class BillValidatorHelper {
  protected void validateIdMatchWithPeriodAndRoomNo(Bill bill) {
    String expectBillId = bill.getRoomNumber() + "-" + bill.getRentalPeriod();
    if (!bill.getId().equals(expectBillId)) {
      throw new IllegalArgumentException(WRONG_BILL_ID);
    }
  }
}
