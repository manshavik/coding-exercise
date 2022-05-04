package com.willington.test.fundinvestment.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.willington.test.fundinvestment.bean.HoldingQuantity;

@Repository
public class HoldingQuantityQueryRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@Transactional
	public int updateWithQuery(HoldingQuantity holdingQuantity) {
		return entityManager
				.createNativeQuery(
						"UPDATE holding_quantity set quantity = quantity + ? where fund_id = ? and holding_id = ?")
				.setParameter(1, holdingQuantity.getQuantity())
				.setParameter(2, holdingQuantity.getId().getFundId())
				.setParameter(3, holdingQuantity.getId().getHoldingId())
				.executeUpdate();
	}
}
