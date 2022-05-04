package com.willington.test.fundinvestment.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.willington.test.fundinvestment.bean.FundHoldingKey;
import com.willington.test.fundinvestment.bean.HoldingQuantity;

public interface HoldingQuantityRepository extends JpaRepository<HoldingQuantity, FundHoldingKey>{

}
