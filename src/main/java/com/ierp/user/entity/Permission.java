package com.ierp.user.entity;

public class Permission {
	private Long id;//ID
	private String permission_name;//权限名称
	private String description;//权限描述
	public Long getId() {
		return id;
	}
	public String getPermission_name() {
		return permission_name;
	}
	public String getDescription() {
		return description;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setPermission_name(String permission_name) {
		this.permission_name = permission_name;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}
