package com.ierp.permissionmodule.user.controller;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ierp.common.beans.BeanUtils;
import com.ierp.common.web.ExtAjaxResponse;
import com.ierp.common.web.ExtjsPageRequest;
import com.ierp.common.web.SessionUtil;
import com.ierp.permissionmodule.group.domain.Group;
import com.ierp.permissionmodule.user.domain.User;
import com.ierp.permissionmodule.user.domain.UserDTO;
import com.ierp.permissionmodule.user.domain.UserQueryDTO;
import com.ierp.permissionmodule.user.service.IUserService;

@JsonIgnoreProperties(ignoreUnknown = true)//忽略未知属性
@RestController
@RequestMapping(value="/user")
public class UserController {
	@Autowired
    private IUserService userService;
    
	/*
	 * http://localhost:8080/index.html#user
	 */
	
	/*
	 * http://localhost:8080/user/findAll
	 * @查询全部员工
	 * @GetMapping("/findAll")
	 * public List<UserDTO> findAllUser(){
	 * 	try {
	 * 		List<UserDTO> dto = new ArrayList<UserDTO>();
	 * 		dto = userService.findAll();
	 * 		return dto;
	 * 	}catch (Exception e) {
	 * 		e.printStackTrace();
	 * 		return null;
	 * 	}
	 * }
	 */

    /*
     * http://localhost:8080/user?page=1&limit=20
     * @查询所有员工并分页
     * @RequestMapping
     * public Page<UserDTO> getUserPage(ExtjsPageRequest pageable) {
     * 	return userService.findAll(pageable.getPageable());
     * }
     */
    @RequestMapping
    public Page<UserDTO> getPage(UserQueryDTO dto,ExtjsPageRequest pageable){
//    	System.out.println("getRole():"+dto.getGroupName());
//    	System.out.println("getSex():"+dto.getSex());
//    	System.out.println("getStatus():"+dto.getStatus());
//    	System.out.println("getUser_num():"+dto.getUser_num());
//    	System.out.println("getUserName():"+dto.getUserName());
//    	System.out.println("getBirthdayFrom():"+dto.getBirthdayFrom());
//    	System.out.println("getBirthdayTo():"+dto.getBirthdayTo());
    	if(dto.getGroupName()==null && dto.getSex()==null && dto.getStatus()==null && dto.getUser_num()==null && dto.getUserName()==null && dto.getBirthdayFrom()==null && dto.getBirthdayTo()==null) {
//    		System.out.println("1");
    		return userService.findAll(pageable.getPageable());
    	}
//		System.out.println("2");
//		System.out.println("sex1:"+dto.getSex());
		if(dto.getSex().equals("true")) {//不可以写“==”，必须用equals()方法
//			System.out.println("sex2:"+dto.getSex());
			dto.setSex("男");
//			System.out.println("男"+dto.getSex());
		}else if(dto.getSex().equals("false")) {
//			System.out.println("sex2:"+dto.getSex());
			dto.setSex("女");
//			System.out.println("女"+dto.getSex());
		}
		if(dto.getGroupName() != "") {
			return userService.findByGroup(dto.getGroupName(), pageable.getPageable());
		}
		return userService.findAll(UserQueryDTO.getWhereClause(dto), pageable.getPageable());
    	
    }

    /*
     * http://localhost:8080/user/personalCenter
     * @查看用户个人信息
     */
    @RequestMapping("/personalCenter")
    public ExtAjaxResponse personalCenter(ExtjsPageRequest pageable, HttpSession session) {
    	try {
    		String userId = SessionUtil.getUserName(session);
        	Map<String,String> map = new IdentityHashMap<String, String>();
        	if(userId != null) {
        		UserDTO dto = userService.findOne(userId); 	
            	map.put("id", dto.getId());
            	map.put("password", dto.getPassword());
            	map.put("profile_pic", dto.getProfile_pic());
            	map.put("userName", dto.getUserName());
            	map.put("user_num", dto.getUser_num());
            	map.put("phone", dto.getPhone());
            	map.put("sex", dto.getSex());
            	map.put("birthday", String.valueOf(dto.getBirthday()));
            	map.put("address", dto.getAddress());
            	map.put("status", dto.getStatus());
            	map.put("role", String.valueOf(dto.getRole()));
        	}
        	return new ExtAjaxResponse(true,map);
    	}catch (Exception e) {
			 e.printStackTrace();
			 return new ExtAjaxResponse(false,"失败！");
		}
    	
    }
    
    /*
     * http://localhost:8080/user/userSave
     * @添加新员工
     */
    @PostMapping(value="/userSave")
  	public ExtAjaxResponse save(@RequestParam("image")MultipartFile profile_pic,HttpServletRequest request,UserDTO dto) {
	  	try {
//	  		System.out.println(request.getSession().getServletContext().getRealPath("/"));//E:\Homework\personal\2018-2019-1\project\e-commerce-erp\src\main\webapp\
//	  		System.out.println(dto);
	  		
//	  		System.out.println(profile_pic.toString());
	  		String img = userService.uploadImgFile(profile_pic, request);
//	  		System.out.println(img);
	  		String path = "/resources/images/user-profile/";
	  		
	  		
	  		if(img == "图片大小不能超过5M！") {
	  			return new ExtAjaxResponse(false,"图片大小不能超过5M！");
	  		}else if(img == "格式错误！只支持jgp或png文件！") {
	  			return new ExtAjaxResponse(false,"格式错误！只支持jgp或png文件！");
	  		}else if(img == "上传失败！"){
	  			return new ExtAjaxResponse(false,"上传失败！");
	  		}else {
	  			if(img == "未选择文件！") {
		  			img = "f15.png";
		  		}
	  			img = path+img;
//	  			System.out.println(img);
	  			dto.setProfile_pic(img);
	  			
	  			String id = dto.getId();
		  		UserDTO findDTO =  userService.findOne(id);
		  		if(findDTO != null) {
		  			return new ExtAjaxResponse(false,"该用户已存在，请更换用户名！");
		  		}else {
		  			if(dto.getPassword() != null) {
		  				byte[] pwd = userService.eccrypt(dto.getPassword());//加密存储
		  				if(pwd != null) {
		  					String password = new String(pwd);
		  					dto.setPassword(password);
		  				}
		  			}
		  			userService.save(dto);
//		  			System.out.println(userService.findOne(id));
		          	return new ExtAjaxResponse(true,"保存成功！");
		  		}
	  		}
	  		
	  		
//	  		String stringRole = json.getString("role").toString();
//	  		List<String> listRole = Arrays.asList(stringRole.split(","));
//	  		
//	  		UserDTO dto = new UserDTO();
//	  		dto.setId(json.getString("id"));
//	  		dto.setPassword(json.getString("password"));
////	  		dto.setProfile_pic(json.getString("profile_pic"));
//	  		dto.setProfile_pic("/resources/images/user-profile/f15.png");
//	  		dto.setUserName(json.getString("userName"));
//	  		dto.setUser_num(json.getString("user_num"));
//	  		dto.setPhone(json.getString("phone"));
//	  		dto.setSex(json.getString("sex"));
//	  		dto.setBirthday(json.getDate("birthday"));
//	  		dto.setAddress(json.getString("address"));
//	  		dto.setStatus(json.getString("status"));
//	  		dto.setRole(listRole);
	  		
	  	  	
	  		
	  	}catch (Exception e) {
			 e.printStackTrace();
			 return new ExtAjaxResponse(false,"保存失败！");
		}
    }
    
    /*
     * http://localhost:8080/user/id
     * @管理员修改员工角色、状态
     */
    @PutMapping(value="{id}",consumes=MediaType.APPLICATION_JSON_VALUE)
    public ExtAjaxResponse update(@PathVariable("id")String id,@RequestBody JSONObject json) {
    	try{
    		UserDTO findDTO = userService.findOne(id);
    		UserDTO dto = new UserDTO();

    		if(findDTO != null) {
    			String stringRole = json.getString("role").toString();
        		if(stringRole != null) {
        			List<String> listRole = Arrays.asList(stringRole.split(","));
        			dto.setRole(listRole);
        		}
        		if(stringRole == "" || stringRole == null){
        			User entity = userService.findById(id).get();
        			List<Group> group = entity.getGroup();
        			if(!group.isEmpty()) {
        				for(int i=0; i<group.size(); i++) {
        					String groupId = group.get(i).getGroupName();
        					List<String> listRole = new ArrayList<String>();
        					listRole.add(groupId);
        					dto.setRole(listRole);
            			}
        			}
        		}
        		
    			dto.setId(json.getString("id"));
          		dto.setPassword(json.getString("password"));
          		dto.setProfile_pic(json.getString("profile_pic"));
          		dto.setUserName(json.getString("userName"));
          		dto.setUser_num(json.getString("user_num"));
          		dto.setPhone(json.getString("phone"));
          		dto.setSex(json.getString("sex"));
          		dto.setBirthday(json.getDate("birthday"));
          		dto.setAddress(json.getString("address"));
          		dto.setStatus(json.getString("status"));
    			
    			BeanUtils.copyProperties(dto, findDTO);
    			userService.save(findDTO);
    		}
    		return new ExtAjaxResponse(true,"更新成功！");
    	}catch (Exception e) {
    		e.printStackTrace();
    		return new ExtAjaxResponse(false,"更新失败！");
		}
    	
    }

    /*
     * @修改个人密码
     */
    @PutMapping("/passwordUpdate")
    public ExtAjaxResponse personalUpdate(HttpSession session,String password) {
    	try {
    		String userId = SessionUtil.getUserName(session);
    		if(userId != null) {
    			UserDTO findDTO = userService.findOne(userId);
    			if(findDTO != null) {
    				findDTO.setPassword(password);
    				userService.save(findDTO);
    			}
    		}
    		return new ExtAjaxResponse(true,"修改成功！");
    	}catch (Exception e) {
			 e.printStackTrace();
			 return new ExtAjaxResponse(false,"失败！");
		}
    }
    
    /*
     * @修改个人信息
     */
    @PutMapping("/personalEdit")
    public ExtAjaxResponse peronalEdit(HttpSession session,@RequestParam("image")MultipartFile profile_pic,HttpServletRequest request,UserDTO dto) {
    	try {
    		String userId = SessionUtil.getUserName(session);
    		if(userId != null) {
    			UserDTO findDTO = userService.findOne(userId);
    			if(findDTO != null) {
    				String img = userService.uploadImgFile(profile_pic, request);
    				String path = "/resources/images/user-profile/";
    				if(img == "图片大小不能超过5M！") {
    		  			return new ExtAjaxResponse(false,"图片大小不能超过5M！");
    		  		}else if(img == "格式错误！只支持jgp或png文件！") {
    		  			return new ExtAjaxResponse(false,"格式错误！只支持jgp或png文件！");
    		  		}else if(img == "上传失败！"){
    		  			return new ExtAjaxResponse(false,"上传失败！");
    		  		}else {
    		  			if(img == "未选择文件！") {
    		  				if(findDTO.getProfile_pic() != null) {
    		  					img = findDTO.getProfile_pic();
    		  				}else {
    		  					img = "f15.png";
    		  				}	
    			  		}
    		  			img = path+img;
    		  			dto.setProfile_pic(img);
    		  			BeanUtils.copyProperties(dto, findDTO);
    		  			userService.save(findDTO);
    		  		}
    			}
    		}
    		return new ExtAjaxResponse(false,"修改成功！");
    	}catch (Exception e) {
			 e.printStackTrace();
			 return new ExtAjaxResponse(false,"修改失败！");
		}
    }
    
    /*
     * http://localhost:8080/user/id
     * @删除离职员工（即把员工状态改成离职）
     */
    @DeleteMapping("{id}")
    public ExtAjaxResponse delete(@PathVariable("id")String id) {
    	try {
    		if(id != null) {
    			userService.deleteById(id);
    		}
    		return new ExtAjaxResponse(true,"删除成功！");
    	}catch (Exception e) {
    		e.printStackTrace();
			return new ExtAjaxResponse(true,"删除失败！");
		}
    }

    /*
     * http://localhost:8080/user/deletes
     * @删除多个员工
     */
    @PostMapping("/deletes")
    public ExtAjaxResponse deleteRows(@RequestParam(name="ids")String[] ids) {
    	try {
    		if(ids != null) {
    			userService.deleteAll(ids);
    		}
    		return new ExtAjaxResponse(true,"批量删除成功！");
    	}catch (Exception e) {
    		e.printStackTrace();
			return new ExtAjaxResponse(true,"批量删除失败！");
		}
    }
    
    
}
