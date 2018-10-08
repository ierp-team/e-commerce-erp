package com.ierp.product.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.ierp.vendor.domain.Vendor;

@Entity
@Table(name="t_vendor_product")
public class Product {
	private Long id;
	private List<Vendor> vendor;
	private String productName;
	private String productPrice;
	private String productDesc;
	private String productPic;	  //图片路径
	private String productSpec;  //规格
	private String productUuid;  //唯一识别号
	private String productStatus;//状态  1.存货  2.缺货  3.下架
	private Integer productStock; //库存
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}
	@ManyToMany
	public List<Vendor> getVendor() {
		return vendor;
	}

	public String getProductName() {
		return productName;
	}

	public String getProductPrice() {
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

	public String getProductStatus() {
		return productStatus;
	}

	public Integer getProductStock() {
		return productStock;
	}
	
	
	
	public void setId(Long id) {
		this.id = id;
	}
	public void setVendor(List<Vendor> vendor) {
		this.vendor = vendor;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public void setProductPrice(String productPrice) {
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
	public void setProductStatus(String productStatus) {
		this.productStatus = productStatus;
	}
	public void setProductStock(Integer productStock) {
		this.productStock = productStock;
	}

}
