package com.willington.test.fundinvestment.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.willington.test.fundinvestment.bean.CommonData;

@Repository
public class InvestorFundRepository {

	@PersistenceContext
    private EntityManager entityManager;
	
	@Transactional
	public void insertWithQuery(CommonData commonData) {
	    entityManager.createNativeQuery("insert into investor_funds(investor_id, fund_id) values(?,?)")
	      .setParameter(1, commonData.getParentId())
	      .setParameter(2, commonData.getChildId())
	      .executeUpdate();
	}
}
