package crom.rental.service;

import crom.rental.entity.Bill;
import crom.rental.repository.RentalBillRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RentalBillService {
    private final RentalBillRepository repo;

    public void saveBill(Bill bill) {
        repo.save(bill);
    }
}
