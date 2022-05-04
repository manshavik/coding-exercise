package com.willington.test.fundinvestment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.willington.test.fundinvestment.bean.HoldingDetails;
import com.willington.test.fundinvestment.bean.ResponseBean;
import com.willington.test.fundinvestment.service.HoldingService;

@RestController
public class HoldingController {

	@Autowired
	private HoldingService holdingService;
	
	@GetMapping("/holding/{id}")
	public HoldingDetails getHoldingDetails(@PathVariable Long id) {
		
		return holdingService.getHoldingDetails(id);
	}
	
	@PutMapping("/holding")
	public EntityModel<ResponseBean<HoldingDetails>> saveHoldingDetails(@RequestBody HoldingDetails holdingDetails){
		
		return holdingService.saveHoldingDetails(holdingDetails);
	}
}
