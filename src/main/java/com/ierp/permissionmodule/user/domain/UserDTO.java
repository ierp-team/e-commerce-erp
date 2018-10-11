package com.ierp.permissionmodule.user.domain;

//import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.format.annotation.DateTimeFormat;

import com.ierp.permissionmodule.group.domain.Group;

public class UserDTO {
	private String id;//ID
    private String password;
   
    private String profile_pic;//头像
    private String userName;//姓名
    private String user_num;//员工编号
    
    private String phone;//手机号码
    private String sex;//性别
    private Date birthday;//出生日期
    private String address;//家庭地址
    private String status;//账号状态
    
//    private String groupId;
    private String groupName;
//    private String type;
    
    
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getProfile_pic() {
		return profile_pic;
	}
	public void setProfile_pic(String profile_pic) {
		this.profile_pic = profile_pic;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUser_num() {
		return user_num;
	}
	public void setUser_num(String user_num) {
		this.user_num = user_num;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	@DateTimeFormat(pattern="yyyy/MM/dd")
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
//	public String getGroupId() {
//		return groupId;
//	}
//	public void setGroupId(String groupId) {
//		this.groupId = groupId;
//	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
//	public String getType() {
//		return type;
//	}
//	public void setType(String type) {
//		this.type = type;
//	}
    
    public static void dtoToEntity(UserDTO dto, User entity) {
    	BeanUtils.copyProperties(dto, entity);
//    	if(dto.getGroupId() != null) {
//    		Group group = new Group();
//    		group.setGroupName(dto.getGroupId());
//    		group.setName(dto.getGroupName());
//    		group.setType(dto.getType());
//    	}
    	if(dto.getGroupName() != null) {
    		Group group = new Group();
    		group.setName(dto.getGroupName());
    	}
    }
    
    public static void entityToDTO(User entity,UserDTO dto) {
    	BeanUtils.copyProperties(entity, dto);
    	List<Group> groupList = entity.getGroup();
    	for(int i=0;i<groupList.size();i++) {
//    		dto.setGroupId(groupList.get(i).getGroupName());
    		dto.setGroupName(groupList.get(i).getName());
//    		dto.setType(groupList.get(i).getType());
    	}
    }
    

    
	@Override
	public String toString() {
		return "UserDTO [id=" + id + ", password=" + password + ", profile_pic=" + profile_pic + ", userName="
				+ userName + ", user_num=" + user_num + ", phone=" + phone + ", sex=" + sex + ", birthday=" + birthday
				+ ", address=" + address + ", status=" + status + ", groupName=" + groupName + "]";
	}
    
    
}
