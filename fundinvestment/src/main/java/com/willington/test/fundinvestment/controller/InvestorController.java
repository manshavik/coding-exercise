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

import com.willington.test.fundinvestment.bean.CommonData;
import com.willington.test.fundinvestment.bean.InvestorDetails;
import com.willington.test.fundinvestment.bean.ResponseBean;
import com.willington.test.fundinvestment.service.InvestorService;

@RestController
public class InvestorController {

	@Autowired
	private InvestorService investorService;
	
	@GetMapping("/investor/{id}")
	public InvestorDetails getInvestorDetails(@PathVariable @NonNull Long id) {
		
		return investorService.getInvestorDetails(id);
	}
	
	@PutMapping("/investor")
	public ResponseBean<InvestorDetails> saveInvestorDetails(@RequestBody @NonNull InvestorDetails investorDetails){
		
		return investorService.saveInvestorDetails(investorDetails);
	}

	@PutMapping("/investor/updatefund")
	public EntityModel<ResponseBean<String>> saveInvestorFundDetails(@RequestBody @NonNull  CommonData investorFundData){
		
		return investorService.saveInvestorFundDetails(investorFundData);
	}
	
	@GetMapping("/investor/marketvalue/{investorId}")
	public EntityModel<ResponseBean<BigDecimal>> getMarketValue(@PathVariable @NonNull Long investorId, 
			@RequestParam(value="excludedHoldings", required = false) Set<Long> excludedHoldings){
		
		return investorService.getMarketValue(investorId, excludedHoldings);
	}

}
