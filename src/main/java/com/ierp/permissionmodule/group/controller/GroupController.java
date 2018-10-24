package com.ierp.permissionmodule.group.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ierp.common.beans.BeanUtils;
import com.ierp.common.web.ExtAjaxResponse;
import com.ierp.common.web.ExtjsPageRequest;
import com.ierp.permissionmodule.group.domain.Group;
import com.ierp.permissionmodule.group.domain.GroupDTO;
import com.ierp.permissionmodule.group.domain.GroupQueryDTO;
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

	
	/*
	 * http://localhost:8080/index.html#group
	 */

	/*
	 * @查询所有角色：下拉列表的内容
	 * http://localhost:8080/role/findAll
	 */
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

	/*
	 * @查询所有角色
	 * GroupQueryDTO dto
	 */
	@RequestMapping
	public Page<GroupDTO> getUserPage(String name,ExtjsPageRequest pageable) {
//		System.out.println(name);
		try {
			if(name==null) {
				return groupService.findAll(pageable.getPageable());
				
			}
			GroupQueryDTO dto = new GroupQueryDTO();
			dto.setGroupName(name);
			return groupService.findAll(GroupQueryDTO.getWhereClause(dto), pageable.getPageable());
		}catch (Exception e) {
			 e.printStackTrace();
			 return null;
		}
		
	}
	
	/*
	 * @添加新角色
	 */
	@PostMapping(value="/roleSave")
	public ExtAjaxResponse save(@RequestBody JSONObject jsonObject) {
		try {
//			System.out.println(jsonObject);
			Group group = new Group();
			String id = jsonObject.getString("groupName");
			String name = jsonObject.getString("name");
			String type = jsonObject.getString("type");
			group.setGroupName(id);
			group.setName(name);
			group.setType(type);
			group.setRev("0");
//			System.out.println(id);
//			System.out.println(name);
//			System.out.println(type);
			JSONArray jsonNavigationNode = jsonObject.getJSONArray("text");
			for(int i=0; i<jsonNavigationNode.size(); i++) {
				Long navigationNodeId = jsonNavigationNode.getLongValue(i);
//				System.out.println(navigationNodeId);
				NavigationNode navigationNode = navigationNodeService.findOne(navigationNodeId).get();
				if(navigationNode != null) {
					group.getChildNodes().add(navigationNode);
//					System.out.println(navigationNode);
					navigationNode.setChecked(true);
					navigationNodeService.save(navigationNode);
//					System.out.println(navigationNode);
				}
			}
			groupService.save(group);
//			System.out.println(group);
			return new ExtAjaxResponse(true,"保存成功！");
		}catch (Exception e) {
			e.printStackTrace();
			return new ExtAjaxResponse(false,"保存失败！");
		}
	}
	
	/*
	 * @修改角色
	 */
	@PutMapping(value="/update")
	public ExtAjaxResponse update(@RequestBody JSONObject jsonObject) {
		try {
			String id = jsonObject.getString("groupName");
			Group findGroup = groupService.findById(id).get();
			Group group = new Group();
			if(findGroup != null) {
				System.out.println(findGroup);
				String name = jsonObject.getString("name");
				String type = jsonObject.getString("type");
				group.setGroupName(id);
				group.setName(name);
				group.setType(type);
				JSONArray jsonNavigationNode = jsonObject.getJSONArray("text");
				for(int i=0; i<jsonNavigationNode.size(); i++) {
					Long navigationNodeId = jsonNavigationNode.getLongValue(i);
					NavigationNode navigationNode = navigationNodeService.findOne(navigationNodeId).get();
					if(navigationNode != null) {
						group.getChildNodes().add(navigationNode);
						navigationNode.setChecked(true);
						navigationNodeService.save(navigationNode);
					}
				}
			}
			BeanUtils.copyProperties(group, findGroup);
			groupService.save(findGroup);
			System.out.println(findGroup);
			return new ExtAjaxResponse(true,"更新成功！");
		}catch (Exception e) {
    		e.printStackTrace();
    		return new ExtAjaxResponse(false,"更新失败！");
		}
	}
	
	/*
	 * @删除角色：把rev从0改成1
	 */
	@DeleteMapping
	public ExtAjaxResponse delete(@RequestBody JSONObject jsonObject) {
		try {
			String id = jsonObject.getString("groupName");
    		if(id != null) {
    			groupService.deleteById(id);
    		}
    		return new ExtAjaxResponse(true,"删除成功！");
    	}catch (Exception e) {
    		e.printStackTrace();
			return new ExtAjaxResponse(true,"删除失败！");
		}
	}
	
	/*
	 * @删除多个员工
	 */
	@PostMapping("/deletes")
    public ExtAjaxResponse deleteRows(@RequestParam(name="ids")String[] ids) {
    	try {
    		if(ids != null) {
    			groupService.deleteAll(ids);
    		}
    		return new ExtAjaxResponse(true,"批量删除成功！");
    	}catch (Exception e) {
    		e.printStackTrace();
			return new ExtAjaxResponse(true,"批量删除失败！");
		}
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
