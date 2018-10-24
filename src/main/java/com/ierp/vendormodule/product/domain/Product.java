package com.ierp.vendormodule.product.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.ierp.vendormodule.vendor.domain.Vendor;

@Entity
@Table(name="t_vendor_product")
public class Product {
	private Long productId;
	private Vendor vendor;
	private String productName;
	private Float productPrice;
	private String productDesc;
	private String productPic;	  //图片路径
	private String productSpec;  //规格
	private String productUuid;  //唯一识别号
	private ProductStatus productStatus;//状态  1.存货  2.缺货  3.下架
	private Integer productStock; //库存
	
	@Id
	@Column(name="product_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Long getProductId() {
		return productId;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="vendorId")
	public Vendor getVendor() {
		return vendor;
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

	public ProductStatus getProductStatus() {
		return productStatus;
	}

	public Integer getProductStock() {
		return productStock;
	}
	
	
	
	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public void setVendor(Vendor vendor) {
		this.vendor = vendor;
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
	
	public void setProductStatus(ProductStatus productStatus) {
		this.productStatus = productStatus;
	}
	
	public void setProductStock(Integer productStock) {
		this.productStock = productStock;
	}

}
