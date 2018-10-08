package com.ierp.goods.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * 公司商品类
 *
 */
@Entity
@Table(name="t_goods")
public class Goods {
	
	private Long goodsId;			//商品id
	private String goodsCode;		//商品编号
	private String goodsName;		//商品名字
	private String goodsPhoto;		//商品图片（路径）
	private String goodsUuid;		//商品标识码
	private String goodsDesc;		//商品描述
	private String goodsSpec;		//商品规格
	private Long vendorId;			//供应商id
	private Float supplyPrice;		//进货价
	private Float salePrice;		//出售价
	private Integer goodsStock;		//库存
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Long getGoodsId() {
		return goodsId;
	}
	@NotNull
	public String getGoodsCode() {
		return goodsCode;
	}
	@NotNull
	public String getGoodsName() {
		return goodsName;
	}
	public String getGoodsPhoto() {
		return goodsPhoto;
	}
	@NotNull
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
	@NotNull
	public Float getSupplyPrice() {
		return supplyPrice;
	}
	@NotNull
	public Float getSalePrice() {
		return salePrice;
	}
	@NotNull
	public Integer getGoodsStock() {
		return goodsStock;
	}
	
	public void setGoodsId(Long goodsId) {
		this.goodsId = goodsId;
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
