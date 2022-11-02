package crom.rental.service.bill;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

import crom.rental.entity.bill.Bill;
import crom.rental.repository.bill.IMongoBillRepository;
import crom.rental.repository.bill.IRentalBillRepository;
import crom.rental.specification.bill.BillCriteria;
import crom.rental.specification.bill.BillSpecification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

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

	public List<Bill> findBillsByConditions(Bill bill) {
		var byRoomNumber = new BillSpecification();
		var byPeriod = new BillSpecification();
		if (isNotBlank(bill.getRoomNumber())) {
			byRoomNumber.setCriteria(new BillCriteria("roomNumber", "=", bill.getRoomNumber()));
		}
		if (isNotBlank(bill.getRentalPeriod())) {
			byPeriod.setCriteria(new BillCriteria("rentalPeriod", "=", bill.getRentalPeriod()));
		}
		return jpaRepo.findAll(byRoomNumber.and(byPeriod));
	}
}
