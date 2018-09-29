package com.ierp.vendormodule.product.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.ierp.vendormodule.vendor.domain.Vendor;

@Entity
@Table(name="t_vendor_product")
public class Product {
	private Long id;
//	private List<Vendor> vendor;
	private String product_name;
	private String product_price;
	private String product_desc;
	private String product_pic;	  //图片路径
	private String product_spec;  //规格
	private String product_uuid;  //唯一识别号
	private String product_status;//状态  1.存货  2.缺货  3.下架
	private Integer product_stock; //库存
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	public String getProduct_name() {
		return product_name;
	}
	public String getProduct_price() {
		return product_price;
	}
	public String getProduct_desc() {
		return product_desc;
	}
	public String getProduct_pic() {
		return product_pic;
	}
	public String getProduct_spec() {
		return product_spec;
	}
	public String getProduct_uuid() {
		return product_uuid;
	}
	public String getProduct_status() {
		return product_status;
	}
	public Integer getProduct_stock() {
		return product_stock;
	}
//	@ManyToMany
//	public List<Vendor> getVendor() {
//		return vendor;
//	}

	
//	public void setVendor(List<Vendor> vendor) {
//		this.vendor = vendor;
//	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public void setProduct_price(String product_price) {
		this.product_price = product_price;
	}
	public void setProduct_desc(String product_desc) {
		this.product_desc = product_desc;
	}
	public void setProduct_pic(String product_pic) {
		this.product_pic = product_pic;
	}
	public void setProduct_spec(String product_spec) {
		this.product_spec = product_spec;
	}
	public void setProduct_uuid(String product_uuid) {
		this.product_uuid = product_uuid;
	}
	public void setProduct_status(String product_status) {
		this.product_status = product_status;
	}
	public void setProduct_stock(Integer product_stock) {
		this.product_stock = product_stock;
	}
}
