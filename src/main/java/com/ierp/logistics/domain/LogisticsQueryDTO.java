package com.ierp.logistics.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import com.ierp.logistics.domain.Logistics;

public class LogisticsQueryDTO {
	
	private String expressNumber;			//物流单号
	private String eorderCode;			//订单编码 OrderCode
	
	private String entireAddress;			//收件人地址
    private String contact;			//联系人（收件人）Receiver.Name
    private String phone;			//收件人手机号码 Receiver.Mobile
	
	
	public String getExpressNumber() {
		return expressNumber;
	}
	public String getEorderCode() {
		return eorderCode;
	}
	public String getEntireAddress() {
		return entireAddress;
	}
	public String getContact() {
		return contact;
	}
	public String getPhone() {
		return phone;
	}
	

	public void setExpressNumber(String expressNumber) {
		this.expressNumber = expressNumber;
	}
	public void setEorderCode(String eorderCode) {
		this.eorderCode = eorderCode;
	}
	public void setEntireAddress(String entireAddress) {
		this.entireAddress = entireAddress;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	
	@SuppressWarnings({ "serial"})
	public static Specification<Logistics> getWhereClause(final LogisticsQueryDTO logisticsQueryDTO) {
		return new Specification<Logistics>() {
			@Override
			public Predicate toPredicate(Root<Logistics> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
			
				List<Predicate> predicate = new ArrayList<>();
				
				if (StringUtils.isNotBlank(logisticsQueryDTO.getExpressNumber())) {
					predicate.add(criteriaBuilder.like(root.get("expressNumber").as(String.class),
							"%" + logisticsQueryDTO.getExpressNumber() + "%"));
				}
				if (StringUtils.isNotBlank(logisticsQueryDTO.getEorderCode())) {
					predicate.add(criteriaBuilder.like(root.get("eorderCode").as(String.class),
							"%" + logisticsQueryDTO.getEorderCode() + "%"));
				}
				if (StringUtils.isNotBlank(logisticsQueryDTO.getEntireAddress())) {
					predicate.add(criteriaBuilder.like(root.get("entireAddress").as(String.class),
							"%" + logisticsQueryDTO.getEntireAddress() + "%"));
				}
				if (StringUtils.isNotBlank(logisticsQueryDTO.getContact())) {
					predicate.add(criteriaBuilder.like(root.get("contact").as(String.class),
							"%" + logisticsQueryDTO.getContact() + "%"));
				}
				if (StringUtils.isNotBlank(logisticsQueryDTO.getPhone())) {
					predicate.add(criteriaBuilder.like(root.get("phone").as(String.class),
							"%" + logisticsQueryDTO.getPhone() + "%"));
				}
				
				Predicate[] pre = new Predicate[predicate.size()];
				return query.where(predicate.toArray(pre)).getRestriction();
			}
		};
	}
	
}
