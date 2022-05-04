package com.willington.test.fundinvestment.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.willington.test.fundinvestment.bean.FundDetails;

public interface FundRepository extends JpaRepository<FundDetails, Long> {

}
