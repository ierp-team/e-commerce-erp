package com.ierp.permissionmodule.user.controller;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ierp.common.web.ExtjsPageRequest;
import com.ierp.permissionmodule.user.domain.UserDTO;
import com.ierp.permissionmodule.user.service.IUserService;

@Controller
@RequestMapping(value="/user")
public class UserController {
	@Autowired
    private IUserService userService;
    
//    @GetMapping("/findAll")
//    public List<UserDTO> findAllUser(){
//    	List<UserDTO> dto = new ArrayList<UserDTO>();
//    	try {
//    		dto = userService.findAll();
//    		return dto;
//    	}catch (Exception e) {
//			e.printStackTrace();
//			return dto;
//		}
//    }
    
    //http://localhost:8080/user/findPage?page=1&limit=20
    @RequestMapping("/findPage")
    public @ResponseBody Page<UserDTO> getUserPage(ExtjsPageRequest pageable) {
		return userService.findAll(pageable.getPageable());
    	
    }

    
    
}
