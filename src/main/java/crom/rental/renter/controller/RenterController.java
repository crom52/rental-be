package crom.rental.renter.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import crom.rental.common.ResultResponse;
import crom.rental.renter.service.RenterService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class RenterController {
	private final RenterService renterService;
	@CrossOrigin
	@GetMapping(value = "/renter", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResultResponse findBillsByConditions(@RequestParam(name = "keyWord") String keyWord) {
		var result = ResultResponse.builder();
		try {
			result.data(renterService.findByKeyword(keyWord)).status(HttpStatus.OK);
		} catch (Exception e) {
			result.message(e.getMessage()).data(List.of());
		}
		return result.build();
	}
}
