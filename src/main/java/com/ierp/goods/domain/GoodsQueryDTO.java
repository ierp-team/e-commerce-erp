package com.ierp.goods.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import com.ierp.goods.domain.Goods;

public class GoodsQueryDTO {
	
	private String goodsCode;
	private String goodsName;
	private String goodsUuid;
	private Long vendorId;
	
	public String getGoodsCode() {
		return goodsCode;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public String getGoodsUuid() {
		return goodsUuid;
	}
	public Long getVendorId() {
		return vendorId;
	}
	
	public void setGoodsCode(String goodsCode) {
		this.goodsCode = goodsCode;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public void setGoodsUuid(String goodsUuid) {
		this.goodsUuid = goodsUuid;
	}
	public void setVendorId(Long vendorId) {
		this.vendorId = vendorId;
	}
	
	@SuppressWarnings({ "serial"})
	public static Specification<Goods> getWhereClause(final GoodsQueryDTO goodsQueryDTO) {
		return new Specification<Goods>() {
			@Override
			public Predicate toPredicate(Root<Goods> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
			
				List<Predicate> predicate = new ArrayList<>();
				
				if (StringUtils.isNotBlank(goodsQueryDTO.getGoodsCode())) {
					predicate.add(criteriaBuilder.like(root.get("goodsCode").as(String.class),
							"%" + goodsQueryDTO.getGoodsCode() + "%"));
				}
				if (StringUtils.isNotBlank(goodsQueryDTO.getGoodsName())) {
					predicate.add(criteriaBuilder.like(root.get("goodsName").as(String.class),
							"%" + goodsQueryDTO.getGoodsName() + "%"));
				}
				if (StringUtils.isNotBlank(goodsQueryDTO.getGoodsUuid())) {
					predicate.add(criteriaBuilder.like(root.get("goodsUuid").as(String.class),
							"%" + goodsQueryDTO.getGoodsUuid() + "%"));
				}
				if (null!=goodsQueryDTO.getVendorId()) {
					predicate.add(criteriaBuilder.equal(root.get("vendorId").as(Long.class),
							goodsQueryDTO.getVendorId()));
				}
				
				Predicate[] pre = new Predicate[predicate.size()];
				return query.where(predicate.toArray(pre)).getRestriction();
			}
		};
	}
	
}
