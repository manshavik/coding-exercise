package com.willington.test.fundinvestment.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Service;

import com.willington.test.fundinvestment.bean.FundDetails;
import com.willington.test.fundinvestment.bean.HoldingQuantity;
import com.willington.test.fundinvestment.bean.ResponseBean;
import com.willington.test.fundinvestment.controller.FundController;
import com.willington.test.fundinvestment.dao.FundRepository;
import com.willington.test.fundinvestment.dao.HoldingQuantityQueryRepository;
import com.willington.test.fundinvestment.dao.HoldingQuantityRepository;
import com.willington.test.fundinvestment.exception.DataNotFoundException;
import com.willington.test.fundinvestment.exception.InvalidDataException;

@Service
public class FundService {

	@Autowired
	private FundRepository fundRepository;
	
	@Autowired
	private HoldingService holdingService;
	
	@Autowired
	private HoldingQuantityRepository holdingQuantityRepository;
	
	@Autowired
	private HoldingQuantityQueryRepository holdingQuantityQueryRepository;
	
	public FundDetails getFundDetails(Long id) {
		Optional<FundDetails> fundDetail = fundRepository.findById(id);
		if(fundDetail.isEmpty())
			throw new DataNotFoundException(String.format("Fund details not found for id {}",id));
		
		return fundDetail.get();
	}

	public boolean validateFundDetails(Set<FundDetails> fundList) {
		List<Long> fundIds = new ArrayList<>();
		for(FundDetails fundDetails: fundList) {
			fundIds.add(fundDetails.getId());
		}
		List<FundDetails> dbList = fundRepository.findAllById(fundIds);
		return dbList==null ? false : fundList.size() == dbList.size();
	}

	@Transactional
	public ResponseBean<FundDetails> saveFundDetails(FundDetails fundDetails) {
		if(fundDetails.getHoldingList()!=null
				&& !fundDetails.getHoldingList().isEmpty()
				&& holdingService.isHoldingDetailsValid(fundDetails.getHoldingList())) {
			throw new InvalidDataException("Invalid holding details provided for the fund");
		}
		
		FundDetails savedDetails = fundRepository.save(fundDetails);
		return new ResponseBean<FundDetails>(savedDetails);
	}

	@Transactional
	public EntityModel<ResponseBean<String>> updateHoldingDetails(HoldingQuantity holdingQuantity) {
		
		if(holdingQuantityQueryRepository.updateWithQuery(holdingQuantity)==0) {
			holdingQuantityRepository.save(holdingQuantity);
		}
		EntityModel<ResponseBean<String>> model = EntityModel.of(new ResponseBean<>("Details Updated"));
		WebMvcLinkBuilder linkToUser = 
				WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(FundController.class).getFundDetails(holdingQuantity.getId().getFundId()));
		model.add(linkToUser.withRel("fund-details"));
		return model;
	}

	public EntityModel<ResponseBean<BigDecimal>> getMarketValue(Long fundId, Set<Long> excludeHoldings) {
		FundDetails fundDetails = getFundDetails(fundId);
		BigDecimal result = BigDecimal.ZERO;
		for(HoldingQuantity holdingQuantity: fundDetails.getHoldingList()) {
			if(excludeHoldings == null 
					|| !excludeHoldings.contains(holdingQuantity.getHoldingDetails().getId()))
				result = result.add(holdingQuantity.getQuantity().multiply(holdingQuantity.getHoldingDetails().getPrice()));
		}
		
		EntityModel<ResponseBean<BigDecimal>> model = EntityModel.of(new ResponseBean<>(result));
		WebMvcLinkBuilder linkToUser = 
				WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(FundController.class).getFundDetails(fundId));
		model.add(linkToUser.withRel("fund-details"));
		return model;
	}

}
