package com.ierp.vendormodule.stockorder.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.format.annotation.DateTimeFormat;

public class StockOrderQueryDTO {
	
		private String StockOrderNumber;

		@DateTimeFormat(pattern="yyyy/MM/dd HH:mm:ss")  
		private Date createTimeStart;

		@DateTimeFormat(pattern="yyyy/MM/dd HH:mm:ss")  
		private Date createTimeEnd;

		
		public String getStockOrderNumber() {
			return StockOrderNumber;
		}

		public void setStockOrderNumber(String stockOrderNumber) {
			StockOrderNumber = stockOrderNumber;
		}

		public Date getCreateTimeStart() {
			return createTimeStart;
		}

		public void setCreateTimeStart(Date createTimeStart) {
			this.createTimeStart = createTimeStart;
		}
		public Date getCreateTimeEnd() {
			return createTimeEnd;
		}

		public void setCreateTimeEnd(Date createTimeEnd) {
			this.createTimeEnd = createTimeEnd;
		}

		@SuppressWarnings({ "serial"})
		public static Specification<StockOrder> getWhereClause(final StockOrderQueryDTO stockOrderQueryDTO) {
			return new Specification<StockOrder>() {
				@Override
				public Predicate toPredicate(Root<StockOrder> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				
					List<Predicate> predicate = new ArrayList<>();
					if (StringUtils.isNotBlank(stockOrderQueryDTO.getStockOrderNumber())) {
						predicate.add(criteriaBuilder.like(root.get("stockOrderNumber").as(String.class),
								"%" + stockOrderQueryDTO.getStockOrderNumber() + "%"));
					}
					if (null!=stockOrderQueryDTO.getCreateTimeStart()) {
						predicate.add(criteriaBuilder.greaterThanOrEqualTo(root.get("createTime").as(Date.class),
								stockOrderQueryDTO.getCreateTimeStart()));
					}
					if (null!=stockOrderQueryDTO.getCreateTimeEnd()) {
						predicate.add(criteriaBuilder.lessThanOrEqualTo(root.get("createTime").as(Date.class),
								stockOrderQueryDTO.getCreateTimeEnd()));
					}
					
					Predicate[] pre = new Predicate[predicate.size()];
					return query.where(predicate.toArray(pre)).getRestriction();
				}
			};
		}
	}
