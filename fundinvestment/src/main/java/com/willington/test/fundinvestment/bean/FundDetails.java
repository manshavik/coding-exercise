package com.willington.test.fundinvestment.bean;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.springframework.lang.NonNull;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class FundDetails {

	@Id
	@GeneratedValue(generator = "sequence-generator")
    @GenericGenerator(
      name = "sequence-generator",
      strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
      parameters = {
        @Parameter(name = "sequence_name", value = "fund_sequence"),
        @Parameter(name = "initial_value", value = "103"),
        @Parameter(name = "increment_size", value = "1")
        }
    )
	private Long id;
	
	@NonNull
	private String name;
	
	@OneToMany(mappedBy = "fundDetails", fetch = FetchType.EAGER)
	private List<HoldingQuantity> holdingList;
	
}
