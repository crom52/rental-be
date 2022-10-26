package crom.rental.service.bill;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

import crom.rental.entity.Bill;
import crom.rental.repository.IMongoBillRepository;
import crom.rental.repository.IRentalBillRepository;
import crom.rental.specification.BillCriteria;
import crom.rental.specification.BillSpecification;
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
