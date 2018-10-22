package com.ierp.vendor.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;


public class VendorQueryDTO {
	
	private	String vendorName;

	public String getVendorName() {
		return vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}
	
	@SuppressWarnings({ "serial"})
	public static Specification<Vendor> getWhereClause(final VendorQueryDTO vendorQueryDTO) {
		return new Specification<Vendor>() {
			@Override
			public Predicate toPredicate(Root<Vendor> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
			
				List<Predicate> predicate = new ArrayList<>();
				if (StringUtils.isNotBlank(vendorQueryDTO.getVendorName())) {
					predicate.add(criteriaBuilder.like(root.get("vendorName").as(String.class),
							"%" + vendorQueryDTO.getVendorName() + "%"));
				}
				Predicate[] pre = new Predicate[predicate.size()];
				return query.where(predicate.toArray(pre)).getRestriction();
			}
		};
	}
}
