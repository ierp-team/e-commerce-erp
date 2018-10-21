package com.ierp.permissionmodule.user.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ierp.permissionmodule.group.domain.Group;

//@JsonIgnoreProperties(ignoreUnknown = true)//忽略未知属性
public class UserDTO {
	private String id;//ID
    private String password;
   
    private String profile_pic;//头像
    private String userName;//姓名
    private String user_num;//员工编号
    
    private String phone;//手机号码
    private String sex;//性别
//    @DateTimeFormat(pattern="yyyy/MM/dd HH:mm:ss")
    @JsonFormat(pattern="yyyy/MM/dd")
//    @DateTimeFormat(pattern="yyyy/MM/dd")
    private Date birthday;//出生日期
    private String address;//家庭地址
    private String status;//账号状态
    
//    private String groupId;
    private List<String> role = new ArrayList<String>();
//      private List<Group> group = new ArrayList<Group>();
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
	
	public List<String> getRole() {
		return role;
	}
	public void setRole(List<String> role) {
		this.role = role;
	}
	
//	@JsonIgnore//不输出
//    public List<Group> getGroup() {
//		return group;
//	}
//	public void setGroup(List<Group> group) {
//		this.group = group;
//	}

	
	public static void dtoToEntity(UserDTO dto, User entity) {
    	BeanUtils.copyProperties(dto, entity);

    }
    
    
	
	public static void entityToDTO(User entity,UserDTO dto) {
    	BeanUtils.copyProperties(entity, dto);
    	List<Group> groupList = entity.getGroup();
    	for(int i=0;i<groupList.size();i++) {
    		dto.getRole().add(groupList.get(i).getName());
    	}
    }
    

    
	public UserDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UserDTO(String id, String password, String profile_pic, String userName, String user_num, String phone,
			String sex, Date birthday, String address, String status, List<String> role) {
		super();
		this.id = id;
		this.password = password;
		this.profile_pic = profile_pic;
		this.userName = userName;
		this.user_num = user_num;
		this.phone = phone;
		this.sex = sex;
		this.birthday = birthday;
		this.address = address;
		this.status = status;
		this.role = role;
	}
	
	
	@Override
	public String toString() {
		return "UserDTO [id=" + id 
					+ ", password=" + password 
					+ ", profile_pic=" + profile_pic 
					+ ", userName=" + userName 
					+ ", user_num=" + user_num 
					+ ", phone=" + phone 
					+ ", sex=" + sex 
					+ ", birthday=" + birthday
					+ ", address=" + address 
					+ ", status=" + status 
					+ ", groupName=" + role
				+ "]";
	}
    
    
}
