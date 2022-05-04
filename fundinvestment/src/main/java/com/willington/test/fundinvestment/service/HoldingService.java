package com.willington.test.fundinvestment.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Service;

import com.willington.test.fundinvestment.bean.HoldingDetails;
import com.willington.test.fundinvestment.bean.HoldingQuantity;
import com.willington.test.fundinvestment.bean.ResponseBean;
import com.willington.test.fundinvestment.controller.HoldingController;
import com.willington.test.fundinvestment.dao.HoldingRepository;
import com.willington.test.fundinvestment.exception.DataNotFoundException;

@Service
public class HoldingService {

	@Autowired
	private HoldingRepository repository;

	public HoldingDetails getHoldingDetails(Long id) {
		Optional<HoldingDetails> holdingDetails = repository.findById(id);
		if (holdingDetails.isEmpty())
			throw new DataNotFoundException(String.format("Holding not found for id {}", id));

		return holdingDetails.get();
	}

	@Transactional
	public EntityModel<ResponseBean<HoldingDetails>> saveHoldingDetails(HoldingDetails holdingDetails) {
		HoldingDetails savedDetails = repository.save(holdingDetails);
		
		WebMvcLinkBuilder linkToUser = 
				WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(HoldingController.class).getHoldingDetails(savedDetails.getId()));
		EntityModel<ResponseBean<HoldingDetails>> model = EntityModel.of(new ResponseBean<>(savedDetails));
		model.add(linkToUser.withRel("user-details"));
		return model;
	}

	public boolean isHoldingDetailsValid(List<HoldingQuantity> holdingList) {
		List<Long> holdingIds = new ArrayList<>();
		for(HoldingQuantity holdingDetails: holdingList) {
			holdingIds.add(holdingDetails.getHoldingDetails().getId());
		}
		List<HoldingDetails> dbList = repository.findAllById(holdingIds);
		return dbList==null ? false : holdingList.size() == dbList.size();
	}

}
