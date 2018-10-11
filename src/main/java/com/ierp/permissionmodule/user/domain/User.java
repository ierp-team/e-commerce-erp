package com.ierp.permissionmodule.user.domain;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import com.ierp.permissionmodule.group.domain.Group;
//import com.ierp.permissionmodule.navigation.domain.NavigationNode;
@Entity
@Table(name="act_id_user")
public class User {
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
     
    
    private List<Group> groupList = new ArrayList<Group>();//角色
    
    
    @Id
    @Column(name="ID_")
    public String getId() {
        return id;
    }
    
    public String getProfile_pic() {
        return profile_pic;
    }
    public String getUserName() {
        return userName;
    }
    public String getUser_num() {
        return user_num;
    }
   
    public String getPhone() {
        return phone;
    }
    public String getSex() {
        return sex;
    }
    @DateTimeFormat(pattern="yyyy/MM/dd")
    public Date getBirthday() {
        return birthday;
    }
    public String getAddress() {
        return address;
    }
    public String getStatus() {
        return status;
    }
    public void setId(String id) {
        this.id = id;
    }
    public void setProfile_pic(String profile_pic) {
        this.profile_pic = profile_pic;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public void setUser_num(String user_num) {
        this.user_num = user_num;
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
    
    @Column(name="PWD_")
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    @ManyToMany
    @JoinTable(name="act_id_membership",joinColumns=@JoinColumn(name="USER_ID_"),inverseJoinColumns=@JoinColumn(name="GROUP_ID_"))
    public List<Group> getGroup() {
        return groupList;
    }

    public void setGroup(List<Group> groupList) {
        this.groupList = groupList;
    }

    
}
