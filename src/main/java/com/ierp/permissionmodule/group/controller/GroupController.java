package com.ierp.permissionmodule.group.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ierp.common.web.ExtAjaxResponse;
import com.ierp.common.web.ExtjsPageRequest;
import com.ierp.permissionmodule.group.domain.Group;
import com.ierp.permissionmodule.group.domain.GroupDTO;
import com.ierp.permissionmodule.group.service.IGroupService;
import com.ierp.permissionmodule.navigation.domain.NavigationNode;
import com.ierp.permissionmodule.navigation.service.INavigationNodeService;
import com.ierp.permissionmodule.user.domain.UserDTO;

@RestController
@RequestMapping("/role")
public class GroupController {

	@Autowired
    private IGroupService groupService;

	@Autowired
    private INavigationNodeService navigationNodeService;

	
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
	@RequestMapping("/init")
    public @ResponseBody ExtAjaxResponse initData() 
    { 
        try {
//            NavigationNode nn1 = new NavigationNode();
//            nn1.setText("EOrder");
//            nn1.setIconCls("x-fa fa-first-order");
//            nn1.setLeaf(false);
//            nn1.setViewType("eOrderPanel");
//            nn1.setRowCls("nav-tree-badge nav-tree-badge-hot");
//            
//            NavigationNode nn2 = new NavigationNode();
//            nn2.setText("初始订单");
//            nn2.setIconCls("x-fa fa-tasks");
//            nn2.setLeaf(true);
//            nn2.setViewType("originalOrderPanel");
//            nn2.setRowCls("nav-tree-badge nav-tree-badge-new");
//            
//            NavigationNode nn3 = new NavigationNode();
//            nn3.setText("未匹配订单");
//            nn3.setIconCls("x-fa fa-balance-scale");
//            nn3.setLeaf(true);
//            nn3.setViewType("noMatchOrderPanel");
//            nn3.setRowCls(null);
//            
//            NavigationNode nn4= new NavigationNode();
//            nn4.setText("已分配订单");
//            nn4.setIconCls("x-fa fa-bookmark");
//            nn4.setLeaf(true);
//            nn4.setViewType("assignedOrderPanel");
//            nn4.setRowCls(null);
//       
//            NavigationNode nn1 = navigationNodeService.findByText("EOrder");
//            NavigationNode nn2 = navigationNodeService.findByText("供应商管理");
//            
//           Group group = groupService.findById("administrator").get();
//           group.setGroupName("administrator");
//           group.setName("管理员");
//           group.getChildNodes().add(nn1);
//           group.getChildNodes().add(nn2);
//           group.getChildNodes().add(nn3);
//           group.getChildNodes().add(nn4);
           
//           navigationNodeService.save(nn1);
//           navigationNodeService.save(nn2);
//           navigationNodeService.save(nn3);
//           navigationNodeService.save(nn4);
           
//           groupService.save(group);
         
            return new ExtAjaxResponse(true,"操作成功！");
        } catch (Exception e) {
             e.printStackTrace();
             return new ExtAjaxResponse(false,"操作失败！");
        }
    } 
}
