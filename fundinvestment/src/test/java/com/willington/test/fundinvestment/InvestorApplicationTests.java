package com.willington.test.fundinvestment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.willington.test.fundinvestment.bean.CommonData;
import com.willington.test.fundinvestment.bean.InvestorDetails;
import com.willington.test.fundinvestment.controller.InvestorController;
import com.willington.test.fundinvestment.dao.InvestorRepository;
import com.willington.test.fundinvestment.service.InvestorService;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
class InvestorApplicationTests {
	
	@Autowired
	InvestorController investorController;
	
	@Autowired
	InvestorService investorService;
	
	@Autowired
	InvestorRepository investorRepository;
	
	@Test
	void testGetInvestorDetailService() {
		assertEquals(100L, investorController.getInvestorDetails(100L).getId());
	}
	
	@Test
	void testPutInvestorDetailService() {
		InvestorDetails details = new InvestorDetails();
		details.setName("Ravi");
		assertNotNull(investorController.saveInvestorDetails(details));
	}
	
	@Test
	void testSaveInvestorFundDetails() {
		CommonData commonData = new CommonData();
		commonData.setParentId(100L);
		commonData.setChildId(1002L);
		investorController.saveInvestorFundDetails(commonData);
		assertEquals(3, investorService.getInvestorDetails(commonData.getParentId()).getFundList().size());
	}

	@Test
	void testInvestorMarketValue() {
		assertEquals(BigDecimal.valueOf(1430).doubleValue(), 
				investorController.getMarketValue(101L, 
						null).getContent().getResult().doubleValue());
		
		assertEquals(BigDecimal.valueOf(830).doubleValue(), 
				investorController.getMarketValue(101L ,
						new HashSet<Long>(Arrays.asList(10000L))).getContent().getResult().doubleValue());
	}
	
}
