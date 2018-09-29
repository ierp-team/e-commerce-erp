package com.ierp.vendor.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="t_vendor")
public class Vendor {
	private Long id;
	private	String vendor_name;
	private String vendor_address;
	private String vendor_phone;
	private String vendor_account;
	private String vendor_password;
	private String vendor_fund;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}
	public String getVendor_name() {
		return vendor_name;
	}
	public String getVendor_address() {
		return vendor_address;
	}
	public String getVendor_phone() {
		return vendor_phone;
	}
	public String getVendor_account() {
		return vendor_account;
	}
	public String getVendor_password() {
		return vendor_password;
	}
	public String getVendor_fund() {
		return vendor_fund;
	}
	
	
	
	public void setId(Long id) {
		this.id = id;
	}
	public void setVendor_name(String vendor_name) {
		this.vendor_name = vendor_name;
	}
	public void setVendor_address(String vendor_address) {
		this.vendor_address = vendor_address;
	}
	public void setVendor_phone(String vendor_phone) {
		this.vendor_phone = vendor_phone;
	}
	public void setVendor_account(String vendor_account) {
		this.vendor_account = vendor_account;
	}
	public void setVendor_password(String vendor_password) {
		this.vendor_password = vendor_password;
	}
	public void setVendor_fund(String vendor_fund) {
		this.vendor_fund = vendor_fund;
	}
	
}
