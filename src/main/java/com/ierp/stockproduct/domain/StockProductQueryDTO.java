package com.ierp.stockproduct.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.ierp.stockorder.domain.StockOrder;

public class StockProductQueryDTO {
	
	private StockOrder stockOrder;
	
	public StockOrder getStockOrder() {
		return stockOrder;
	}

	public void setStockOrder(StockOrder stockOrder) {
		this.stockOrder = stockOrder;
	}
	
	@SuppressWarnings({ "serial"})
	public static Specification<StockProduct> getWhereClause(final StockProductQueryDTO stockProductQueryDTO) {
		return new Specification<StockProduct>() {
			@Override
			public Predicate toPredicate(Root<StockProduct> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
			
				List<Predicate> predicate = new ArrayList<>();
				if (null!=stockProductQueryDTO.getStockOrder()) {
					predicate.add(criteriaBuilder.equal(root.get("stockOrder").as(StockOrder.class),
							stockProductQueryDTO.getStockOrder()));
				}
				Predicate[] pre = new Predicate[predicate.size()];
				return query.where(predicate.toArray(pre)).getRestriction();
			}
		};
	}
}
