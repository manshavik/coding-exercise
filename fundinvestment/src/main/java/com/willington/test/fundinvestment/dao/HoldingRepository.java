package com.willington.test.fundinvestment.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.willington.test.fundinvestment.bean.HoldingDetails;

public interface HoldingRepository extends JpaRepository<HoldingDetails, Long>{

}
