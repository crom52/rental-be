package crom.rental.controller;


import crom.rental.common.ResultResponse;
import crom.rental.entity.Bill;
import crom.rental.service.RentalBillService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class IndexController {
    private final RentalBillService rentalBillService;


    @CrossOrigin
    @PutMapping("/bill")
    public ResultResponse saveBill(@RequestBody Bill bill) {
        var result = ResultResponse.builder();
        try {
            result.data(rentalBillService.saveBill(bill)).status(HttpStatus.OK);
        } catch (Exception e) {
            result.message(e.getMessage());
        }
        return result.build();
    }
}
