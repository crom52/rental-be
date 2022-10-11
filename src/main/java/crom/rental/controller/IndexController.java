package crom.rental.controller;


import crom.rental.entity.BaseEntity;
import crom.rental.entity.Bill;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import crom.rental.service.RentalBillService;

@RestController
@RequiredArgsConstructor
public class IndexController {
    private final RentalBillService rentalBillService;

    @CrossOrigin
    @PostMapping("/bill")
    public void saveBill(@RequestBody Bill bill) {
        rentalBillService.saveBill(bill);
    }
}
