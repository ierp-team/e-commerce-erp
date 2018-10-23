package com.ierp.vendormodule.vendor.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ierp.vendormodule.product.domain.Product;

@Entity
@Table(name="t_vendor")
public class Vendor {
	private Long vendorId;
	private	String vendorName;
	private String vendorAddress;
	private String vendorPhone;
	private String vendorAccount;
	private String vendorPassword;
	private String vendorFund;
	private List<Product> product = new ArrayList<Product>();
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Long getVendorId() {
		return vendorId;
	}

	public String getVendorName() {
		return vendorName;
	}

	public String getVendorAddress() {
		return vendorAddress;
	}

	public String getVendorPhone() {
		return vendorPhone;
	}

	public String getVendorAccount() {
		return vendorAccount;
	}

	public String getVendorPassword() {
		return vendorPassword;
	}

	public String getVendorFund() {
		return vendorFund;
	}
	@JsonIgnore
	@OneToMany(mappedBy = "vendor", cascade = CascadeType.ALL)
	public List<Product> getProduct() {
		return product;
	}

	public void setVendorId(Long vendorId) {
		this.vendorId = vendorId;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}

	public void setVendorAddress(String vendorAddress) {
		this.vendorAddress = vendorAddress;
	}

	public void setVendorPhone(String vendorPhone) {
		this.vendorPhone = vendorPhone;
	}

	public void setVendorAccount(String vendorAccount) {
		this.vendorAccount = vendorAccount;
	}

	public void setVendorPassword(String vendorPassword) {
		this.vendorPassword = vendorPassword;
	}

	public void setVendorFund(String vendorFund) {
		this.vendorFund = vendorFund;
	}

	public void setProduct(List<Product> product) {
		this.product = product;
	}
	
}
