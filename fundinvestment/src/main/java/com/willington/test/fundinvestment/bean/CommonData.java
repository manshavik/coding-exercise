package com.willington.test.fundinvestment.bean;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class CommonData {

	private Long parentId;
	private Long childId;
	private BigDecimal quantity;
	
}
