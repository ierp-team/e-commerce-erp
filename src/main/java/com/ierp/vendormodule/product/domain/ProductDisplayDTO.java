package com.ierp.vendormodule.product.domain;

import com.ierp.common.beans.BeanUtils;

public class ProductDisplayDTO {
	private Long productId;
	private Long vendorId;
	private String vendorName;
	private String productName;
	private Float productPrice;
	private String productDesc;
	private String productPic;	  //图片路径
	private String productSpec;  //规格
	private String productUuid;  //唯一识别号
//	private String productStatus;//状态  1.存货  2.缺货  3.下架
	private Integer productStock; //库存
	
	public Long getProductId() {
		return productId;
	}

	public Long getVendorId() {
		return vendorId;
	}

	public String getVendorName() {
		return vendorName;
	}

	public String getProductName() {
		return productName;
	}

	public Float getProductPrice() {
		return productPrice;
	}

	public String getProductDesc() {
		return productDesc;
	}

	public String getProductPic() {
		return productPic;
	}

	public String getProductSpec() {
		return productSpec;
	}

	public String getProductUuid() {
		return productUuid;
	}

//	public String getProductStatus() {
//		return productStatus;
//	}

	public Integer getProductStock() {
		return productStock;
	}
	
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	
	public void setVendorId(Long vendorId) {
		this.vendorId = vendorId;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public void setProductPrice(Float productPrice) {
		this.productPrice = productPrice;
	}

	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}

	public void setProductPic(String productPic) {
		this.productPic = productPic;
	}

	public void setProductSpec(String productSpec) {
		this.productSpec = productSpec;
	}

	public void setProductUuid(String productUuid) {
		this.productUuid = productUuid;
	}

//	public void setProductStatus(String productStatus) {
//		this.productStatus = productStatus;
//	}

	public void setProductStock(Integer productStock) {
		this.productStock = productStock;
	}
	
	//后到前：1.针对“前端”设计的数据封装对象(查询)
	public static void entityToDto(Product entity,ProductDisplayDTO dto ) {
		BeanUtils.copyProperties(entity, dto);
		if(entity.getVendor()!=null) {
			dto.setVendorId(entity.getVendor().getVendorId());
			dto.setVendorName(entity.getVendor().getVendorName());
		}
	}
	
	//前到后：2.维护多个对象 的数据 以及 对象之间的关联关系 (创建关联、更新关联)
		public static void dtoToEntity(ProductDisplayDTO dto ,Product entity) {
			BeanUtils.copyProperties(dto, entity);
		}	
}
