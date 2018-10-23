package com.ierp.permissionmodule.group.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ierp.common.web.ExtjsPageRequest;
import com.ierp.permissionmodule.group.domain.GroupDTO;
import com.ierp.permissionmodule.group.service.IGroupService;
import com.ierp.permissionmodule.user.domain.UserDTO;

@RestController
@RequestMapping("/role")
public class GroupController {
	@Autowired
    private IGroupService groupService;
	
	//http://localhost:8080/index.html#group
	
	//http://localhost:8080/role/findAll
	@GetMapping("/findAll")
	public List<GroupDTO> findAllGroupName(){
		List<GroupDTO> dto = new ArrayList<GroupDTO>();
		try {
			dto = groupService.findAll();
			return dto;
		}catch (Exception e) {
			e.printStackTrace();
			return dto;
		}	
	}

	//http://localhost:8080/role/findPage
	@RequestMapping("/findPage")
    public Page<GroupDTO> getUserPage(ExtjsPageRequest pageable) {
		return groupService.findAll(pageable.getPageable());
    	
    }
}
