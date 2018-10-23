package com.ierp.logistics.domain;

import com.ierp.common.beans.BeanUtils;

/**
 * 物流信息响应DTO
 *
 */
public class LogisticsDTO {
	
	private Long id;			//物流id
	private String expresscoCode;			//快递公司编码
	private String expresscoName;			//快递公司名
	private String expressNumber;			//物流单号
	private Float freight;			//运费
	
	//物流参数
	private String eorderCode;			//订单编码 OrderCode
	
	//发件人信息
	private String senderEntireAddress;			//发件人地址
	private String senderContact;			//联系人（发件人）Sender.Name
	private String senderPhone;			//发件人号码 Sender.Mobile
	
	//客户（收件人）信息
	private String entireAddress;			//收件人地址
    private String contact;			//联系人（收件人）Receiver.Name
    private String phone;			//收件人手机号码 Receiver.Mobile
    
    //货物信息
	private String goodsType;       //商品名称 Commodity.GoodsName
	private Double weight;			//包裹总重量kg Weight
	
	
	/* -------------------------entityToDto------------------------------- */
	public static void entityToDto(Logistics entity, LogisticsDTO dto) {
		if (null!=entity.getLogisticsCompany()) {
			dto.setExpresscoCode(entity.getLogisticsCompany().getExpresscoCode());
			dto.setExpresscoName(entity.getLogisticsCompany().getExpresscoName());
		}
		BeanUtils.copyProperties(entity, dto);
	}
	/* ------------------------------------------------------------------- */
	
	public Long getId() {
		return id;
	}
	public String getExpresscoCode() {
		return expresscoCode;
	}
	public String getExpresscoName() {
		return expresscoName;
	}
	public String getExpressNumber() {
		return expressNumber;
	}
	public Float getFreight() {
		return freight;
	}
	public String getEorderCode() {
		return eorderCode;
	}
	public String getSenderEntireAddress() {
		return senderEntireAddress;
	}
	public String getSenderContact() {
		return senderContact;
	}
	public String getSenderPhone() {
		return senderPhone;
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
	public String getGoodsType() {
		return goodsType;
	}
	public Double getWeight() {
		return weight;
	}
	
	
	public void setId(Long id) {
		this.id = id;
	}
	public void setExpresscoCode(String expresscoCode) {
		this.expresscoCode = expresscoCode;
	}
	public void setExpresscoName(String expresscoName) {
		this.expresscoName = expresscoName;
	}
	public void setExpressNumber(String expressNumber) {
		this.expressNumber = expressNumber;
	}
	public void setFreight(Float freight) {
		this.freight = freight;
	}
	public void setEorderCode(String eorderCode) {
		this.eorderCode = eorderCode;
	}
	public void setSenderEntireAddress(String senderEntireAddress) {
		this.senderEntireAddress = senderEntireAddress;
	}
	public void setSenderContact(String senderContact) {
		this.senderContact = senderContact;
	}
	public void setSenderPhone(String senderPhone) {
		this.senderPhone = senderPhone;
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
	public void setGoodsType(String goodsType) {
		this.goodsType = goodsType;
	}
	public void setWeight(Double weight) {
		this.weight = weight;
	}

}
