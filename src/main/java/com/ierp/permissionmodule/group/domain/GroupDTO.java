package com.ierp.permissionmodule.group.domain;

import org.springframework.beans.BeanUtils;

public class GroupDTO {
	private String groupName;
    private String name;
    private String type;

	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
    
    public static void entityToDTO(Group entity,GroupDTO dto) {
    	BeanUtils.copyProperties(entity, dto);
    	
    }
    
	@Override
	public String toString() {
		return "GroupDTO [groupId=" + groupName + ", groupName=" + groupName + ", type=" + type + "]";
	}
    
    

}
