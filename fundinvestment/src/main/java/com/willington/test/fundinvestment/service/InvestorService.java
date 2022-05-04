package com.willington.test.fundinvestment.service;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.willington.test.fundinvestment.bean.CommonData;
import com.willington.test.fundinvestment.bean.FundDetails;
import com.willington.test.fundinvestment.bean.HoldingQuantity;
import com.willington.test.fundinvestment.bean.InvestorDetails;
import com.willington.test.fundinvestment.bean.ResponseBean;
import com.willington.test.fundinvestment.controller.InvestorController;
import com.willington.test.fundinvestment.dao.InvestorFundRepository;
import com.willington.test.fundinvestment.dao.InvestorRepository;
import com.willington.test.fundinvestment.exception.DataNotFoundException;
import com.willington.test.fundinvestment.exception.InvalidDataException;

@Service
public class InvestorService {

	@Autowired
	private InvestorRepository repository;
	
	@Autowired
	private FundService fundService;
	
	@Autowired
	private InvestorFundRepository investorFundRepository;
	
	public InvestorDetails getInvestorDetails(Long id) {
		Optional<InvestorDetails> investorDetails = repository.findById(id);
		if(investorDetails.isEmpty()) {
			throw new DataNotFoundException(
					String.format("Investor not found for id {}", id));
		}
		return investorDetails.get();
	}

	@Transactional
	public ResponseBean<InvestorDetails> saveInvestorDetails(InvestorDetails investorDetails) {
		
		if(investorDetails.getFundList()!=null
				&& !investorDetails.getFundList().isEmpty()
				&& fundService.validateFundDetails(investorDetails.getFundList())){
			throw new InvalidDataException("Invalid fund data provided for the investor");
		}
		
		InvestorDetails savedDetails = repository.save(investorDetails);
		return new ResponseBean<InvestorDetails>(savedDetails);
	}

	@Transactional
	public EntityModel<ResponseBean<String>> saveInvestorFundDetails(CommonData investorFundData) {
		if(getInvestorDetails(investorFundData.getParentId())== null 
				|| fundService.getFundDetails(investorFundData.getChildId())==null) {
			throw new DataNotFoundException("Invalid investor or fund id");
		}
		
		investorFundRepository.insertWithQuery(investorFundData);
		
		EntityModel<ResponseBean<String>> model = EntityModel.of(new ResponseBean<>("Details updated"));
		WebMvcLinkBuilder linkToUser = 
				WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(InvestorController.class).getInvestorDetails(investorFundData.getParentId()));
		model.add(linkToUser.withRel("investor-details"));
		return model;
	}

	public EntityModel<ResponseBean<BigDecimal>> getMarketValue(Long investorId, Set<Long> excludedHoldings) {
		InvestorDetails investorDetails = getInvestorDetails(investorId);
		BigDecimal result = BigDecimal.ZERO;
		
		if(investorDetails != null && investorDetails.getFundList() != null
				&& !investorDetails.getFundList().isEmpty()) {
			for(FundDetails fundDetails: investorDetails.getFundList()) {
				for(HoldingQuantity holdingQuantity:fundDetails.getHoldingList()) {
					if(excludedHoldings==null 
							|| !excludedHoldings.contains(holdingQuantity.getHoldingDetails().getId()))
						result = result.add(holdingQuantity.getQuantity().multiply(holdingQuantity.getHoldingDetails().getPrice()));
				}
			}
		}
		
		EntityModel<ResponseBean<BigDecimal>> model = EntityModel.of(new ResponseBean<>(result));
		WebMvcLinkBuilder linkToUser = 
				WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(InvestorController.class).getInvestorDetails(investorId));
		model.add(linkToUser.withRel("investor-details"));
		return model;
	}

}
