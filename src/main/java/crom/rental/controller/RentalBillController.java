package crom.rental.controller;

import static java.util.List.of;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import crom.rental.common.ResultResponse;
import crom.rental.entity.bill.Bill;
import crom.rental.service.bill.RentalBillService;
import lombok.RequiredArgsConstructor;

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

	@CrossOrigin
	@GetMapping(value = "/bills", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResultResponse findBillsByConditions(@RequestBody Bill request) {
		var result = ResultResponse.builder();
		try {
			result.data(rentalBillService.findBillsByConditions(request)).status(HttpStatus.OK);
		} catch (Exception e) {
			result.message(e.getMessage()).data(of());
		}
		return result.build();
	}
}
