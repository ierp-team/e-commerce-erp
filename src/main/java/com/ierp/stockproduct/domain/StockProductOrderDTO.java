package com.ierp.stockproduct.domain;

import java.util.Date;

import com.ierp.common.beans.BeanUtils;

public class StockProductOrderDTO {

	private Long stockProductId;
	private Long productId;
	private String productName;
	private Float productPrice;
	private Integer stockProductQuan;
//	private Float stockProductAmmount;
	
	private Long stockOrderId;
	private Date createTime;
	private String stockOrderNumber;
//	private StockOrderStatus stockOrderStatus;
	private Float stockOrderSum;
	private String address;
	private String phoneNumber;
	
	
	public Long getStockProductId() {
		return stockProductId;
	}
	
	public Long getProductId() {
		return productId;
	}

	public String getProductName() {
		return productName;
	}
	
	public Float getProductPrice() {
		return productPrice;
	}
	
	public Integer getStockProductQuan() {
		return stockProductQuan;
	}
	
	public Long getStockOrderId() {
		return stockOrderId;
	}
	
	public Date getCreateTime() {
		return createTime;
	}
	
	public String getStockOrderNumber() {
		return stockOrderNumber;
	}
	
	public Float getStockOrderSum() {
		return stockOrderSum;
	}
	
	public String getAddress() {
		return address;
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	public void setStockProductId(Long stockProductId) {
		this.stockProductId = stockProductId;
	}
	
	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	public void setProductPrice(Float productPrice) {
		this.productPrice = productPrice;
	}
	
	public void setStockProductQuan(Integer stockProductQuan) {
		this.stockProductQuan = stockProductQuan;
	}
	
	public void setStockOrderId(Long stockOrderId) {
		this.stockOrderId = stockOrderId;
	}
	
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	public void setStockOrderNumber(String stockOrderNumber) {
		this.stockOrderNumber = stockOrderNumber;
	}
	
	public void setStockOrderSum(Float stockOrderSum) {
		this.stockOrderSum = stockOrderSum;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	//后到前：1.针对“前端”设计的数据封装对象(查询)
	public static void entityToDto(StockProduct entity,StockProductOrderDTO dto ) {
		BeanUtils.copyProperties(entity, dto);
		if(entity.getStockOrder()!=null) {
			
			dto.setStockOrderId(entity.getStockOrder().getStockOrderId());
			dto.setStockOrderNumber(entity.getStockOrder().getStockOrderNumber());
			
			dto.setProductId(entity.getProduct().getProductId());	
			dto.setProductName(entity.getProduct().getProductName());
			dto.setProductPrice(entity.getProduct().getProductPrice());
		}
	}
		
	//前到后：2.维护多个对象 的数据 以及 对象之间的关联关系 (创建关联、更新关联)
	public static void dtoToEntity(StockProductOrderDTO dto ,StockProduct entity) {
		BeanUtils.copyProperties(dto, entity);
	}	
}
