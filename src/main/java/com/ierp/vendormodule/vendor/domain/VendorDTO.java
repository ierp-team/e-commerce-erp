package com.ierp.vendormodule.vendor.domain;

import com.ierp.common.beans.BeanUtils;

public class VendorDTO {
	private Long vendorId;
	private	String vendorName;
	private String vendorAddress;
	private String vendorPhone;
	private String vendorFund;
	private String vendorAccount;
	private String vendorPassword;
	
	public Long getVendorId() {
		return vendorId;
	}
	public void setVendorId(Long vendorId) {
		this.vendorId = vendorId;
	}
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
	
	//后到前：1.针对“前端”设计的数据封装对象(查询)
		public static void entityToDto(Vendor entity,VendorDTO dto ) {
			BeanUtils.copyProperties(entity, dto);
			if(entity.getUser()!=null) {
				dto.setVendorAccount(entity.getUser().getId());
				dto.setVendorPassword(entity.getUser().getPassword());
			}
		}
}
