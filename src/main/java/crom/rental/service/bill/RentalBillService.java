package crom.rental.service.bill;

import crom.rental.entity.Bill;
import crom.rental.repository.IMongoBillRepository;
import crom.rental.repository.IRentalBillRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeoutException;

import static crom.rental.common.ExceptionMessage.WRONG_BILL_ID;

@Service
@Slf4j
@RequiredArgsConstructor
@RabbitListener(queues = "save-bill-queue")
public class RentalBillService implements MessageListener {
    private final IRentalBillRepository jpaRepo;
    private final IMongoBillRepository mongoRepo;
    private final BillProducer rabbitSender;


    @Transactional
    public Bill saveBill(Bill bill) throws IOException, TimeoutException {
//    validateIdMatchWithPeriodAndRoomNo(bill);
        Bill savaData = bill.setBaseEntity(bill);
        Bill saveData = bill;
        mongoRepo.save(saveData);
        jpaRepo.save(saveData);
        rabbitSender.publish(bill);
        return bill;
    }


    private void validateIdMatchWithPeriodAndRoomNo(Bill bill) {
        String expectBillId = bill.getRoomNumber() + bill.getRentalPeriod();
        if (!bill.getId().equals(expectBillId)) {
            throw new IllegalArgumentException(WRONG_BILL_ID);
        }
    }


    public Bill findBillInfo(String roomNumber, String rentalPeriod) {
        String id = roomNumber + "-" + rentalPeriod;
        return jpaRepo.findById(id).orElse(null);
    }


    public List<Bill> findBillByPeriod(String rentalPeriod) {
        return null;
    }

    @Override
    public void onMessage(Message message) {
        System.out.println("ok222");
    }
}
