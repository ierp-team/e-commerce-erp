package com.ierp.product.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import com.ierp.vendor.domain.Vendor;

public class ProductQueryDTO {

	private String productName;
	private String productUuid;  //唯一识别号
	private Vendor vendor;

	public String getProductName() {
		return productName;
	}
	
	public String getProductUuid() {
		return productUuid;
	}
	
	public Vendor getVendor() {
		return vendor;
	}
	
	public void setProductName(String productName) {
		this.productName = productName;
	}

	public void setProductUuid(String productUuid) {
		this.productUuid = productUuid;
	}
	
	public void setVendor(Vendor vendor) {
		this.vendor = vendor;
	}

	@SuppressWarnings({ "serial"})
	public static Specification<Product> getWhereClause(final ProductQueryDTO productQueryDTO) {
		return new Specification<Product>() {
			@Override
			public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
			
				List<Predicate> predicate = new ArrayList<>();
				if (StringUtils.isNotBlank(productQueryDTO.getProductName())) {
					predicate.add(criteriaBuilder.like(root.get("productName").as(String.class),
							"%" + productQueryDTO.getProductName() + "%"));
				}
				if (StringUtils.isNotBlank(productQueryDTO.getProductUuid())) {
					predicate.add(criteriaBuilder.like(root.get("productUuid").as(String.class),
							"%" + productQueryDTO.getProductUuid() + "%"));
				}
				if (null!=productQueryDTO.getVendor()) {
					predicate.add(criteriaBuilder.equal(root.get("vendor").as(Vendor.class),
							productQueryDTO.getVendor()));
				}
				Predicate[] pre = new Predicate[predicate.size()];
				return query.where(predicate.toArray(pre)).getRestriction();
			}
		};
	}
}
