package crom.rental.service.bill;

import static crom.rental.common.ExceptionMessage.WRONG_BILL_ID;
import crom.rental.entity.Bill;
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
