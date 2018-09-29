package com.ierp.expressco.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import com.ierp.expressco.domain.Expressco;

public class ExpresscoQueryDTO {
	
	private String expresscoCode;
	private String expresscoName;
	

	public String getexpresscoCode() {
		return expresscoCode;
	}
	public String getExpresscoCode() {
		return expresscoCode;
	}
	public String getExpresscoName() {
		return expresscoName;
	}

	public void setexpresscoCode(String expresscoCode) {
		this.expresscoCode = expresscoCode;
	}
	public void setExpresscoCode(String expresscoCode) {
		this.expresscoCode = expresscoCode;
	}
	public void setExpresscoName(String expresscoName) {
		this.expresscoName = expresscoName;
	}

	@SuppressWarnings({ "serial"})
	public Specification<Expressco> getWhereClause(final ExpresscoQueryDTO expresscoQueryDTO) {
		return new Specification<Expressco>() {
			@Override
			public Predicate toPredicate(Root<Expressco> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
			
				List<Predicate> predicate = new ArrayList<>();
				
				if (StringUtils.isNotBlank(expresscoQueryDTO.getexpresscoCode())) {
					predicate.add(criteriaBuilder.like(root.get("expresscoCode").as(String.class),
							"%" + expresscoQueryDTO.getExpresscoCode() + "%"));
				}
				if (StringUtils.isNotBlank(expresscoQueryDTO.getexpresscoCode())) {
					predicate.add(criteriaBuilder.like(root.get("expresscoName").as(String.class),
							"%" + expresscoQueryDTO.getExpresscoName() + "%"));
				}
				
				Predicate[] pre = new Predicate[predicate.size()];
				return query.where(predicate.toArray(pre)).getRestriction();
			}
		};
	}
	
}
