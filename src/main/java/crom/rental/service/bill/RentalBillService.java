package crom.rental.service.bill;

import crom.rental.entity.Bill;
import crom.rental.repository.IMongoBillRepository;
import crom.rental.repository.IRentalBillRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.domain.Specification;
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
    private final BillValidatorHelper validatorHelper;

    @Transactional
    public Bill saveBill(Bill bill) {
        validatorHelper.validateIdMatchWithPeriodAndRoomNo(bill);
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






    public List<Bill> findBillByPeriod(String rentalPeriod) {
//        BooleanExpression customerHasBirthday = customer.birthday.eq(today);
//
//        Specification<Bill> specification = "" ;
//        Specification<Bill> condition = Specification.where(specification).and()
//        return jpaRepo.findAll()
        return null;
    }

    public List<Bill> findBillByRoomNumber(String roomNumber) {
        return null;
    }


}
