package com.willington.test.fundinvestment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.willington.test.fundinvestment.bean.HoldingDetails;
import com.willington.test.fundinvestment.controller.HoldingController;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
class HoldingApplicationTests {

	@Autowired
	HoldingController holdingController;
	
	@Test
	void testholdingDetailService() {
		assertEquals(10000L, holdingController.getHoldingDetails(10000L).getId());
	}
	
	@Test
	void testPutHoldingDetailService() {
		HoldingDetails details = new HoldingDetails();
		details.setName("Publicis");
		details.setPrice(BigDecimal.ONE);
		assertNotNull(holdingController.saveHoldingDetails(details));
	}
}
