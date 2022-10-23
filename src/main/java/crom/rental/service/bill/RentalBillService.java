package crom.rental.service.bill;

import crom.rental.entity.Bill;
import crom.rental.repository.IMongoBillRepository;
import crom.rental.repository.IRentalBillRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

import static crom.rental.common.ExceptionMessage.WRONG_BILL_ID;

@Component
@Slf4j
@RequiredArgsConstructor
public class RentalBillService {
    private final IRentalBillRepository jpaRepo;
    private final IMongoBillRepository mongoRepo;
    private final BillProducer rabbitSender;

    @Transactional
    public Bill saveBill(Bill bill) {
        validateIdMatchWithPeriodAndRoomNo(bill);
        Bill saveData = bill.setBaseEntity(bill);
        mongoRepo.save(saveData);
        jpaRepo.save(saveData);
        rabbitSender.publish(bill);
        return bill;
    }

    public Bill findBillInfo(String roomNumber, String rentalPeriod) {
        String id = roomNumber + "-" + rentalPeriod;
        return jpaRepo.findById(id).orElse(null);
    }


    private void validateIdMatchWithPeriodAndRoomNo(Bill bill) {
        String expectBillId = bill.getRoomNumber() + "-" + bill.getRentalPeriod();
        if (!bill.getId().equals(expectBillId)) {
            throw new IllegalArgumentException(WRONG_BILL_ID);
        }
    }




    public List<Bill> findBillByPeriod(String rentalPeriod) {
        return null;
    }

}
