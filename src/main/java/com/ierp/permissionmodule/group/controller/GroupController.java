package com.ierp.permissionmodule.group.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ierp.permissionmodule.group.domain.GroupDTO;
import com.ierp.permissionmodule.group.service.IGroupService;

@RestController
@RequestMapping("/role")
public class GroupController {
	@Autowired
    private IGroupService groupService;
	
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

}
