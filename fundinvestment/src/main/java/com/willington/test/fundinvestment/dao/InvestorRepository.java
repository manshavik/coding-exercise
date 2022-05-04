package com.willington.test.fundinvestment.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.willington.test.fundinvestment.bean.InvestorDetails;

public interface InvestorRepository extends JpaRepository<InvestorDetails, Long>{
	
}
