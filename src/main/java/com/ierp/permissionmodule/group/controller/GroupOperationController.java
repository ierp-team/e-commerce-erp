package com.ierp.permissionmodule.group.controller;

import org.activiti.engine.IdentityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ierp.common.web.ExtAjaxResponse;
import com.ierp.permissionmodule.group.domain.Group;
import com.ierp.permissionmodule.group.repository.GroupRepository;
import com.ierp.permissionmodule.navigation.domain.NavigationNode;
import com.ierp.permissionmodule.navigation.service.NavigationNodeService;

@Controller
@RequestMapping(value="/group")
public class GroupOperationController {
     
    @Autowired
    private IdentityService identityService;
    @Autowired
    private GroupRepository groupRepository;
    
    @Autowired
    private NavigationNodeService navigationNodeService;
    
    @RequestMapping("/init")
    public @ResponseBody ExtAjaxResponse initData() 
    { 
        try {
            NavigationNode nn1 = new NavigationNode();
            nn1.setText("供应商管理模块");
            nn1.setIconCls("x-fa fa-balance-scale");
            nn1.setLeaf(false);
            nn1.setViewType("supplier");
            nn1.setRowCls("nav-tree-badge nav-tree-badge-hot");
            
            NavigationNode nn2 = new NavigationNode();
            nn2.setText("财务管理模块模块");
            nn2.setIconCls("x-fa fa-balance-scale");
            nn2.setLeaf(true);
            nn2.setViewType("finance");
            nn2.setRowCls("nav-tree-badge nav-tree-badge-new");
            
           Group group = new Group();
           group.setGroupName("ghbKing");
           group.setName("至尊管理员");
           group.getChildNodes().add(nn1);
           group.getChildNodes().add(nn2);
           
           navigationNodeService.save(nn1);
           navigationNodeService.save(nn2);
           
           groupRepository.save(group);
         
            return new ExtAjaxResponse(true,"操作成功！");
        } catch (Exception e) {
             e.printStackTrace();
             return new ExtAjaxResponse(false,"操作失败！");
        }
    } 
}
