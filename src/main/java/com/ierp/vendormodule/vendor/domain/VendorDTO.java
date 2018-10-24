package com.ierp.vendormodule.vendor.domain;

import com.ierp.common.beans.BeanUtils;
import com.ierp.vendormodule.product.domain.Product;
import com.ierp.vendormodule.product.domain.ProductDisplayDTO;

public class VendorDTO {
	
	private	String vendorName;
	private String vendorAddress;
	private String vendorPhone;
	private String vendorFund;
	private String vendorAccount;
	private String vendorPassword;
	public String getVendorName() {
		return vendorName;
	}
	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}
	public String getVendorAddress() {
		return vendorAddress;
	}
	public void setVendorAddress(String vendorAddress) {
		this.vendorAddress = vendorAddress;
	}
	public String getVendorPhone() {
		return vendorPhone;
	}
	public void setVendorPhone(String vendorPhone) {
		this.vendorPhone = vendorPhone;
	}
	public String getVendorFund() {
		return vendorFund;
	}
	public void setVendorFund(String vendorFund) {
		this.vendorFund = vendorFund;
	}
	public String getVendorAccount() {
		return vendorAccount;
	}
	public void setVendorAccount(String vendorAccount) {
		this.vendorAccount = vendorAccount;
	}
	public String getVendorPassword() {
		return vendorPassword;
	}
	public void setVendorPassword(String vendorPassword) {
		this.vendorPassword = vendorPassword;
	}
	public static void dtoToEntity(VendorDTO dto ,Vendor entity) {
		BeanUtils.copyProperties(dto, entity);
	}
}
