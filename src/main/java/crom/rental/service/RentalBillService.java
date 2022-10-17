package crom.rental.service;

import crom.rental.entity.Bill;
import crom.rental.repository.IMongoBillRepository;
import crom.rental.repository.IRentalBillRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class RentalBillService {
    private final IRentalBillRepository jpaRepo;
    private final IMongoBillRepository mongoRepo;

    @Transactional
    public Bill saveBill(Bill bill) {
        Bill savaData = bill.setBaseEntity(bill);
        mongoRepo.save(savaData);
        return jpaRepo.save(savaData);
    }
}
