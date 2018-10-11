package com.ierp.permissionmodule;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit4.SpringRunner;

import com.ierp.common.web.ExtjsPageRequest;
import com.ierp.permissionmodule.group.domain.Group;
import com.ierp.permissionmodule.group.service.IGroupService;
import com.ierp.permissionmodule.user.domain.User;
import com.ierp.permissionmodule.user.domain.UserDTO;
import com.ierp.permissionmodule.user.service.IUserService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTest {
	@Autowired
    private IUserService userService;
    
    @Autowired
    private IGroupService groupService;
	
	@Test
	public void initData() 
    { 
        try {            	
            User user1 = new User();
            user1.setId("ghb");
            user1.setPassword("gh");
            user1.setUserName("高浩博");
            user1.setUser_num("001");
            user1.setPhone("110");
            user1.setSex("男");
            user1.setBirthday(new Date());
            user1.setAddress("广东");
            user1.setStatus("在职");
            
        	Group group1 = groupService.findById("superAdministrator").get();
            user1.getGroup().add(group1);
            
            
            User user2 = new User();
            user2.setId("lzm");
            user2.setPassword("123");
            user2.setUserName("刘镇民");
            user2.setUser_num("002");
            user2.setPhone("110");
            user2.setSex("男");
            user2.setBirthday(new Date());
            user2.setAddress("广东");
            user2.setStatus("在职");
            
            Group group2 = groupService.findById("administrator").get();
            user2.getGroup().add(group2);
            
            
            User user3 = new User();
            user3.setId("xmk");
            user3.setPassword("123");
            user3.setUserName("徐明楷");
            user3.setUser_num("003");
            user3.setPhone("110");
            user3.setSex("男");
            user3.setBirthday(new Date());
            user3.setAddress("广东");
            user3.setStatus("在职");
            
            Group group3 = groupService.findById("salesperson").get();
            user3.getGroup().add(group3);
            
            
            User user4 = new User();
            user4.setId("klx");
            user4.setPassword("123");
            user4.setUserName("孔丽霞");
            user4.setUser_num("004");
            user4.setPhone("110");
            user4.setSex("女");
            user4.setBirthday(new Date());
            user4.setAddress("广东");
            user4.setStatus("在职");
            
            Group group4 = groupService.findById("procurementStaff").get();
            user4.getGroup().add(group4);
            
            
            User user5 = new User();
            user5.setId("test1");
            user5.setPassword("123");
            user5.setUserName("仓库1");
            user5.setUser_num("005");
            user5.setPhone("110");
            user5.setSex("女");
            user5.setBirthday(new Date());
            user5.setAddress("广东");
            user5.setStatus("在职");
            
            Group group5 = groupService.findById("warehouseStaff").get();
            user5.getGroup().add(group5);
            
            
            userService.save(user1);
            userService.save(user2);
            userService.save(user3);
            userService.save(user4);
            userService.save(user5);

//            return new ExtAjaxResponse(true,"操作成功！");
            System.out.print("操作成功");
        } catch (Exception e) {
             e.printStackTrace();
//             return new ExtAjaxResponse(false,"操作失败！");
             System.out.print("操作失败");
        }
    } 

	@Test
	public void groupUpdate() {

		try {
			Group entity = new Group();
			String id = "管理员";
			entity = groupService.findById(id).get();
//			System.out.println(entity.toString());
			entity.setType("klx");
			groupService.save(entity);
			System.out.println("成功！");
		}catch (Exception e) {
			e.printStackTrace();
			System.out.print("失败！");
		}
	}
	
	@Test
	public void userFindAll() {
		try {
//			List<UserDTO> dto = new ArrayList<UserDTO>();
//			dto = userService.findAll();
//			System.out.println(dto.toString());
			ExtjsPageRequest pageable = new ExtjsPageRequest();
			pageable.setPage(1);
			pageable.setLimit(20);
			Page<UserDTO> page = userService.findAll(pageable.getPageable());
			System.out.println(page);
		}catch (Exception e) {
			e.printStackTrace();
			System.out.print("失败！");
		}
	}
}
