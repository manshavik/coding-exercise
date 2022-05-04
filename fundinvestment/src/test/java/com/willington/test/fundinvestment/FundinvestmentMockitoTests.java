package com.willington.test.fundinvestment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.willington.test.fundinvestment.bean.FundDetails;
import com.willington.test.fundinvestment.bean.HoldingDetails;
import com.willington.test.fundinvestment.bean.InvestorDetails;
import com.willington.test.fundinvestment.controller.FundController;
import com.willington.test.fundinvestment.controller.HoldingController;
import com.willington.test.fundinvestment.controller.InvestorController;
import com.willington.test.fundinvestment.dao.FundRepository;
import com.willington.test.fundinvestment.dao.HoldingRepository;
import com.willington.test.fundinvestment.dao.InvestorRepository;

@SpringBootTest
class FundinvestmentMockitoTests {
	
	@Autowired
	InvestorController investorController;
	
	@MockBean
	InvestorRepository investorRepository;
	
	@Autowired
	FundController fundController;
	
	@MockBean
	FundRepository fundRepository;

	@Autowired
	HoldingController holdingController;
	
	@MockBean
	HoldingRepository holdingRepository;
	
	@Test
	void contextLoads() {
	}
	
	@Test
	void testInvestorDetailService() {
		Long id = 1L;
		InvestorDetails details = new InvestorDetails();
		details.setId(id);
		details.setName("Test");
		when(investorRepository.findById(id)).thenReturn(Optional.of(details));
		assertEquals(id, investorController.getInvestorDetails(id).getId());
	}

	@Test
	void testFundDetailService() {
		Long id = 1L;
		FundDetails details = new FundDetails();
		details.setId(id);
		details.setName("Test");
		when(fundRepository.findById(id)).thenReturn(Optional.of(details));
		assertEquals(id, fundController.getFundDetails(id).getId());
	}
	
	@Test
	void testholdingDetailService() {
		Long id = 1L;
		HoldingDetails details = new HoldingDetails();
		details.setId(id);
		details.setName("Test");
		when(holdingRepository.findById(id)).thenReturn(Optional.of(details));
		assertEquals(id, holdingController.getHoldingDetails(id).getId());
	}
}
