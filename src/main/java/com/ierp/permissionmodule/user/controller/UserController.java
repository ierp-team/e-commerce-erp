package com.ierp.permissionmodule.user.controller;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.activiti.engine.IdentityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ierp.common.web.ExtAjaxResponse;
import com.ierp.permissionmodule.group.domain.Group;
import com.ierp.permissionmodule.group.repository.GroupRepository;
import com.ierp.permissionmodule.navigation.service.NavigationNodeService;
import com.ierp.permissionmodule.user.domain.User;
import com.ierp.permissionmodule.user.repository.UserRepository;

@Controller
@RequestMapping(value="/user")
public class UserController {
     
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private GroupRepository groupRepository;
    
    
    @RequestMapping("/init")
    public @ResponseBody ExtAjaxResponse initData() 
    { 
        try {
            
            List<Group> groupList = new ArrayList<Group>();            
            User user1 = new User();
            user1.setId("ghb");
            user1.setPassword("ghb");
            user1.setPhone("110");
            
            User user2 = new User();
            user2.setId("hb");
            user2.setPassword("hb");
            user2.setPhone("11");
            
            Group g1 = new Group();
            g1.setGroupName("King");
            g1.setName("至尊");
            
//          g1.getUserList().add(user1);
            user1.getGroup().add(g1);

            groupRepository.save(g1);
            userRepository.save(user1);
            userRepository.save(user2);
            
         
            return new ExtAjaxResponse(true,"操作成功！");
        } catch (Exception e) {
             e.printStackTrace();
             return new ExtAjaxResponse(false,"操作失败！");
        }
    } 
}
