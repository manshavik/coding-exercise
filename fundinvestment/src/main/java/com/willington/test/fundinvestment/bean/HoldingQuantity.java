package com.willington.test.fundinvestment.bean;

import java.math.BigDecimal;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Setter
@EqualsAndHashCode
@Entity
public class HoldingQuantity{

	@EmbeddedId
	@Getter
	private FundHoldingKey id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("fundId")
	@JoinColumn(name = "fund_id")
	private FundDetails fundDetails;
	
	@ManyToOne
	@MapsId("holdingId")
	@JoinColumn(name = "holding_id")
	@Getter
	@Setter
	private HoldingDetails holdingDetails;
	
	@Getter
	private BigDecimal quantity;

}
