package com.ierp.goods.domain;

import com.ierp.common.beans.BeanUtils;

public class GoodsDTO {

	private Long id;			//商品id
	private String goodsCode;		//商品编号
	private String goodsName;		//商品名字
	private String goodsPhoto;		//商品图片（路径）
	private String goodsUuid;		//商品标识码
	private String goodsDesc;		//商品描述
	private String goodsSpec;		//商品规格
	private Long vendorId;		//供应商id
	private String vendorName;		//供应商名
	private Float supplyPrice;		//进货价
	private Float salePrice;		//出售价
	private Integer goodsStock;		//库存
	
	
	/* -------------------------entityToDto------------------------------- */
	public static void entityToDto(Goods entity, GoodsDTO dto) {
		if (null!=entity.getVendor()) {
			dto.setVendorId(entity.getVendor().getVendorId());
			dto.setVendorName(entity.getVendor().getVendorName());
		}
		BeanUtils.copyProperties(entity, dto);
	}
	/* ------------------------------------------------------------------- */
	
	/* -------------------------entityToDto------------------------------- */
	public static void dtoToEntity(GoodsDTO dto, Goods entity) {
		BeanUtils.copyProperties(dto, entity);
	}
	/* ------------------------------------------------------------------- */
	
	public Long getId() {
		return id;
	}
	public String getGoodsCode() {
		return goodsCode;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public String getGoodsPhoto() {
		return goodsPhoto;
	}
	public String getGoodsUuid() {
		return goodsUuid;
	}
	public String getGoodsDesc() {
		return goodsDesc;
	}
	public String getGoodsSpec() {
		return goodsSpec;
	}
	public Long getVendorId() {
		return vendorId;
	}
	public String getVendorName() {
		return vendorName;
	}
	public Float getSupplyPrice() {
		return supplyPrice;
	}
	public Float getSalePrice() {
		return salePrice;
	}
	public Integer getGoodsStock() {
		return goodsStock;
	}
	
	
	public void setId(Long id) {
		this.id = id;
	}
	public void setGoodsCode(String goodsCode) {
		this.goodsCode = goodsCode;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public void setGoodsPhoto(String goodsPhoto) {
		this.goodsPhoto = goodsPhoto;
	}
	public void setGoodsUuid(String goodsUuid) {
		this.goodsUuid = goodsUuid;
	}
	public void setGoodsDesc(String goodsDesc) {
		this.goodsDesc = goodsDesc;
	}
	public void setGoodsSpec(String goodsSpec) {
		this.goodsSpec = goodsSpec;
	}
	public void setVendorId(Long vendorId) {
		this.vendorId = vendorId;
	}
	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}
	public void setSupplyPrice(Float supplyPrice) {
		this.supplyPrice = supplyPrice;
	}
	public void setSalePrice(Float salePrice) {
		this.salePrice = salePrice;
	}
	public void setGoodsStock(Integer goodsStock) {
		this.goodsStock = goodsStock;
	}
	
}
