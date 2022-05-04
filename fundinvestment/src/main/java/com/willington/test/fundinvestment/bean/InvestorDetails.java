package com.willington.test.fundinvestment.bean;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.springframework.lang.NonNull;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class InvestorDetails {

	@Id
	@GeneratedValue(generator = "sequence-generator")
    @GenericGenerator(
      name = "sequence-generator",
      strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
      parameters = {
        @Parameter(name = "sequence_name", value = "investor_sequence"),
        @Parameter(name = "initial_value", value = "103"),
        @Parameter(name = "increment_size", value = "1")
        }
    )
	private Long id;
	
	@NonNull
	private String name;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "investor_funds", 
				joinColumns = @JoinColumn(name = "investor_id"), 
				inverseJoinColumns = @JoinColumn(name = "fund_id"))
	private Set<FundDetails> fundList;

}
