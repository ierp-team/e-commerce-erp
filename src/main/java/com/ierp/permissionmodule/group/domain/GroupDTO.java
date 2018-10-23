package com.ierp.permissionmodule.group.domain;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.ierp.permissionmodule.navigation.domain.NavigationNode;


public class GroupDTO {
	private String groupName;
    private String name;
    private String type;
    
    private List<String> text = new ArrayList<String>();

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
    
    public List<String> getText() {
		return text;
	}
	public void setText(List<String> text) {
		this.text = text;
	}
	
	
	public static void entityToDTO(Group entity,GroupDTO dto) {
    	BeanUtils.copyProperties(entity, dto);
    	List<NavigationNode> navigationNode = entity.getChildNodes();
    	for(int i=0; i<navigationNode.size(); i++) {
    		dto.getText().add(navigationNode.get(i).getText());
    	}
    }
	public static void dtoToEntity(GroupDTO dto, Group entity) {
		BeanUtils.copyProperties(dto, entity);
	}
    
	@Override
	public String toString() {
		return "GroupDTO [groupId=" + groupName + ", groupName=" + groupName + ", type=" + type + "]";
	}
	public GroupDTO(String groupName, String name, String type, List<String> text) {
		super();
		this.groupName = groupName;
		this.name = name;
		this.type = type;
		this.text = text;
	}
	public GroupDTO() {
		super();
	}
    
	
    

}
