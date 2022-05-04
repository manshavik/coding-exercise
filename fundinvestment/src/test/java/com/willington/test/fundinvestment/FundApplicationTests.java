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

import com.willington.test.fundinvestment.bean.FundDetails;
import com.willington.test.fundinvestment.bean.FundHoldingKey;
import com.willington.test.fundinvestment.bean.HoldingDetails;
import com.willington.test.fundinvestment.bean.HoldingQuantity;
import com.willington.test.fundinvestment.controller.FundController;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
class FundApplicationTests {

	@Autowired
	private FundController fundController;

	
	@Test
	void testFundDetailService() {
		assertEquals(1000L, fundController.getFundDetails(1000L).getId());
	}
	
	@Test
	void testPutFundDetailService() {
		FundDetails details = new FundDetails();
		details.setName("Sappient");
		assertNotNull(fundController.saveFundDetails(details));
	}
	
	@Test
	void testSaveFundHoldingDetails() {
		HoldingQuantity holdingQuantity = new HoldingQuantity();
		holdingQuantity.setQuantity(BigDecimal.ONE);
		
		FundHoldingKey key = new FundHoldingKey();
		key.setFundId(1001L);
		key.setHoldingId(10001L);
		holdingQuantity.setId(key);
		
		FundDetails fundDetails = new FundDetails();
		fundDetails.setId(key.getFundId());
		holdingQuantity.setFundDetails(fundDetails);
		
		HoldingDetails holdingDetails = new HoldingDetails();
		holdingDetails.setId(key.getHoldingId());
		holdingQuantity.setHoldingDetails(holdingDetails);
		
		fundController.updateHoldingDetails(holdingQuantity);
		assertEquals(3, fundController.getFundDetails(key.getFundId()).getHoldingList().size());
	}

	@Test
	void testInvestorMarketValue() {
		assertEquals(BigDecimal.valueOf(1030).doubleValue(), 
				fundController.getMarketValue(1000L, 
						null).getContent().getResult().doubleValue());
		
		assertEquals(BigDecimal.valueOf(655).doubleValue(), 
				fundController.getMarketValue(1000L,
						new HashSet<Long>(Arrays.asList(10000L))).getContent().getResult().doubleValue());
	}
}
