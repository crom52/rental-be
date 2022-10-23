package crom.rental.controller;


import crom.rental.common.ResultResponse;
import crom.rental.entity.Bill;
import crom.rental.service.bill.RentalBillService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class RentalBillController {
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

    @CrossOrigin
    @GetMapping("/{roomNumber}/{rentalPeriod}/previous-bill")
    public ResultResponse findBillInfo(@PathVariable("roomNumber") String roomNumber,
                                       @PathVariable("rentalPeriod") String rentalPeriod) {
        var result = ResultResponse.builder();
        try {
            result.data(rentalBillService.findBillInfo(roomNumber, rentalPeriod)).status(HttpStatus.OK);
        } catch (Exception e) {
            result.message(e.getMessage()).data(null);
        }
        return result.build();
    }
}
