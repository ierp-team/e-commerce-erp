package com.ierp.vendormodule.stockproduct.domain;

import com.ierp.common.beans.BeanUtils;


public class StockProductDisplayDTO {
	private Long stockProductId;
	private	Long productId;
	private	String productName;
	private	Integer stockProductQuan;
	private	Float productPrice;
	private String productSpec;
	private Float stockProductAmmount;
	public Long getStockProductId() {
		return stockProductId;
	}
	public void setStockProductId(Long stockProductId) {
		this.stockProductId = stockProductId;
	}
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public Integer getStockProductQuan() {
		return stockProductQuan;
	}
	public void setStockProductQuan(Integer stockProductQuan) {
		this.stockProductQuan = stockProductQuan;
	}
	public Float getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(Float productPrice) {
		this.productPrice = productPrice;
	}
	public String getProductSpec() {
		return productSpec;
	}
	public void setProductSpec(String productSpec) {
		this.productSpec = productSpec;
	}
	public Float getStockProductAmmount() {
		return stockProductAmmount;
	}
	public void setStockProductAmmount(Float stockProductAmmount) {
		this.stockProductAmmount = stockProductAmmount;
	}
	
	public static void entityToDto(StockProduct entity,StockProductDisplayDTO dto ) {
		BeanUtils.copyProperties(entity, dto);
		if(entity.getProduct()!=null) {
			dto.setProductId(entity.getProduct().getProductId());
			dto.setProductName(entity.getProduct().getProductName());
			dto.setProductPrice(entity.getProduct().getProductPrice());
			dto.setProductSpec(entity.getProduct().getProductSpec());
		}
	}
	
	//前到后：2.维护多个对象 的数据 以及 对象之间的关联关系 (创建关联、更新关联)
		public static void dtoToEntity(StockProductDisplayDTO dto ,StockProduct entity) {
			BeanUtils.copyProperties(dto, entity);
		}
}
