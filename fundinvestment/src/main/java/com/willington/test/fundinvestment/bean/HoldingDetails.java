package com.willington.test.fundinvestment.bean;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import lombok.Data;

@Data
@Entity
public class HoldingDetails {

	@Id
	@GeneratedValue(generator = "sequence-generator")
    @GenericGenerator(
      name = "sequence-generator",
      strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
      parameters = {
        @Parameter(name = "sequence_name", value = "holding_sequence"),
        @Parameter(name = "initial_value", value = "103"),
        @Parameter(name = "increment_size", value = "1")
        }
    )
	private Long id;
	
	private String name;
	
	private BigDecimal price;
	
}