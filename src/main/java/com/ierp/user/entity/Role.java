package com.ierp.user.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="t_role")
public class Role {
	private Long id;//角色ID
	private String role_name;//角色名称
	private String role_num;//角色编号
	private String description;//角色描述
	private Role parent;
	private List<Role> childrens = new ArrayList<Role>();
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}
	public String getRole_name() {
		return role_name;
	}
	public String getRole_num() {
		return role_num;
	}
	public String getDescription() {
		return description;
	}
	@ManyToOne(cascade=CascadeType.ALL)
	public Role getParent() {
		return parent;
	}
	@OneToMany(cascade=CascadeType.ALL,mappedBy="parent")
	public List<Role> getChildrens() {
		return childrens;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}
	public void setRole_num(String role_num) {
		this.role_num = role_num;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setParent(Role parent) {
		this.parent = parent;
	}
	public void setChildrens(List<Role> childrens) {
		this.childrens = childrens;
	}
	
	
}
