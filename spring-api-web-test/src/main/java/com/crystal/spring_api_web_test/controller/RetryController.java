package com.crystal.spring_api_web_test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.crystal.spring_api_web_test.service.CalculationService;

@Controller
public class RetryController {
	@Autowired
	private CalculationService calculationService;
	
	@GetMapping("/retry")
	@ResponseStatus(HttpStatus.OK)
	//@Async
	public void retry(@RequestParam int request) {
		calculationService.process(request);
	}
}
