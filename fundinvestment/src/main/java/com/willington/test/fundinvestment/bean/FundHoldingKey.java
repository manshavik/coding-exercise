package com.willington.test.fundinvestment.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Data;

@Data
@Embeddable
public class FundHoldingKey implements Serializable{

	private static final long serialVersionUID = 1836612181112270942L;

	@Column(name = "fund_id")
	private Long fundId;
	
	@Column(name = "holding_id")
	private Long holdingId;

}
