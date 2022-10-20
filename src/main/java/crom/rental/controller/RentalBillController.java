package crom.rental.controller;


import crom.rental.common.ResultResponse;
import crom.rental.entity.Bill;
import crom.rental.service.RentalBillService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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

  @GetMapping("/previous-period-info/{roomNumber}/{rentalPeriod}")
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
