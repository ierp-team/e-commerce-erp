package com.ierp.permissionmodule.group.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import com.ierp.permissionmodule.navigation.domain.NavigationNode;
@Entity
@Table(name="act_id_group")
@SQLDelete(sql = "update act_id_group set REV_ = '1' where ID_ = ?")
@Where(clause = "REV_  != '1' or REV_ = null")
public class Group {
    private String groupName;
    private String rev;
    private String name;
    private String type;
    //private List<User> userList = new ArrayList<User>() ;
    
    private List<NavigationNode> childNodes = new ArrayList<NavigationNode>();
    
    
    
    @Id
    @Column(name="ID_")
    public String getGroupName() {
        return groupName;
    }
    @Column(name="REV_")
    public String getRev() {
        return rev;
    }
    @Column(name="NAME_")
    public String getName() {
        return name;
    }
    @Column(name="TYPE_")
    public String getType() {
        return type;
    }
    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinTable(name = "t_group_operation", joinColumns ={@JoinColumn(name = "groupName")},inverseJoinColumns = {@JoinColumn(name = "navigationNodeId")})
    public List<NavigationNode> getChildNodes() {
        return childNodes;
    }
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
    public void setRev(String rev) {
        this.rev = rev;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setType(String type) {
        this.type = type;
    }
    public void setChildNodes(List<NavigationNode> childNodes) {
        this.childNodes = childNodes;
    }
//    @ManyToMany(mappedBy="groupList")
//    public List<User> getUserList() {
//        return userList;
//    }
//    public void setUserList(List<User> userList) {
//        this.userList = userList;
//    }
    
    public Group(String groupName, String rev, String name, String type, List<NavigationNode> childNodes) {
    	super();
    	this.groupName = groupName;
    	this.rev = rev;
    	this.name = name;
    	this.type = type;
    	this.childNodes = childNodes;
    }
    public Group() {
    	super();
    }
    
	@Override
	public String toString() {
		return "Group [groupName=" + groupName 
					+ ", rev=" + rev 
					+ ", name=" + name 
					+ ", type=" + type 
//					+ ", childNodes="+ childNodes 
				+ "]";
	}
   
}
