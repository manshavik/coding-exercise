package com.willington.test.fundinvestment.controller;

import java.math.BigDecimal;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.willington.test.fundinvestment.bean.FundDetails;
import com.willington.test.fundinvestment.bean.HoldingQuantity;
import com.willington.test.fundinvestment.bean.ResponseBean;
import com.willington.test.fundinvestment.service.FundService;

@RestController
public class FundController {

	@Autowired
	private FundService fundService;
	
	@GetMapping("/fund/{id}")
	public FundDetails getFundDetails(@PathVariable @NonNull Long id) {
		
		return fundService.getFundDetails(id);
	}
	
	@PutMapping("/fund")
	public ResponseBean<FundDetails> saveFundDetails(@RequestBody @NonNull FundDetails details) {
		
		return fundService.saveFundDetails(details);
	}
	
	@PutMapping("/fund/updateholding")
	public EntityModel<ResponseBean<String>> updateHoldingDetails(@RequestBody @NonNull HoldingQuantity details) {
	
		return fundService.updateHoldingDetails(details);
	}
	
	@GetMapping("/fund/marketvalue/{fundId}")
	public EntityModel<ResponseBean<BigDecimal>> getMarketValue(@PathVariable @NonNull Long fundId,
			@RequestParam Set<Long> excludeHoldings){
		
		return fundService.getMarketValue(fundId, excludeHoldings);
	}
}
