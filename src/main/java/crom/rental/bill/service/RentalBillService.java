package crom.rental.bill.service;

import static java.lang.Integer.parseInt;
import static java.lang.String.valueOf;
import static java.util.Optional.of;
import static org.apache.commons.lang3.StringUtils.isNotBlank;
import static org.apache.commons.lang3.StringUtils.leftPad;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

import crom.rental.bill.entity.Bill;
import crom.rental.bill.repository.IMongoBillRepository;
import crom.rental.bill.repository.IRentalBillRepository;
import crom.rental.bill.specification.BillCriteria;
import crom.rental.bill.specification.BillSpecification;
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
//		mongoRepo.save(saveData);
		jpaRepo.save(saveData);
		Optional<Bill> nextPeriodBill = prepareNextPeriodData(bill);
		nextPeriodBill.ifPresent(jpaRepo::save);
//		rabbitSender.publish(bill);
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

	private Optional<Bill> prepareNextPeriodData(Bill currentBill) {
		Bill nextPeriodBill = new Bill();

		String[] currentPeriodId = currentBill.getId().split("-");
		String roomNo = currentPeriodId[0];
		String month = currentPeriodId[1];
		String year = currentPeriodId[2];
		String newPeriod;
		if (month.equals("12")) {
			newPeriod = "01-" + (parseInt(year) + 1);
		} else {
			newPeriod = leftPad(valueOf(parseInt(month) + 1), 2, "0") + "-" + year;
		}
		nextPeriodBill.setRentalPeriod(newPeriod);
		nextPeriodBill.setId(roomNo + "-" + newPeriod);
		nextPeriodBill.setRoomNumber(currentBill.getRoomNumber());
		nextPeriodBill.setOldElecNumber(currentBill.getCurrentElecNumber());
		nextPeriodBill.setOldWaterNumber(currentBill.getNewWaterNumber());
		nextPeriodBill.setBaseEntity(nextPeriodBill);
		return of(nextPeriodBill);
	}
}
