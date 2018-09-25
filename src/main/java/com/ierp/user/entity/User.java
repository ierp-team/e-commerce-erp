package com.ierp.user.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="t_user")
public class User {
	private Long id;//ID
	private String profile_pic;//头像
	private String userName;//姓名
	private Long user_num;//员工编号
	private Role role;//角色
	private String phone;//手机号码
	private String sex;//性别
	private Date birthday;//出生日期
	private String address;//家庭地址
	private String status;//账号状态
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}
	public String getProfile_pic() {
		return profile_pic;
	}
	public String getUserName() {
		return userName;
	}
	public Long getUser_num() {
		return user_num;
	}
	@ManyToOne(cascade=CascadeType.ALL)
	public Role getRole() {
		return role;
	}
	public String getPhone() {
		return phone;
	}
	public String getSex() {
		return sex;
	}
	@JsonFormat(pattern="yyyy/MM/dd",timezone="GMT+8")
	public Date getBirthday() {
		return birthday;
	}
	public String getAddress() {
		return address;
	}
	public String getStatus() {
		return status;
	}
	
	
	public void setId(Long id) {
		this.id = id;
	}
	public void setProfile_pic(String profile_pic) {
		this.profile_pic = profile_pic;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public void setUser_num(Long user_num) {
		this.user_num = user_num;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
}
