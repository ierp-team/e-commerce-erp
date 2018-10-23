package com.ierp.permissionmodule;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ierp.permissionmodule.group.domain.Group;
import com.ierp.permissionmodule.group.domain.GroupDTO;
import com.ierp.permissionmodule.group.service.IGroupService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GroupControllerTest {
	@Autowired
    private IGroupService groupService;
	
	@Test
	public void GroupInitData() {
		try {
//			Group g1 = new Group();
//			g1.setGroupName("superAdministrator");
//            g1.setName("超级管理员");
//            g1.setType("有最高权限");
//            
//            Group g2 = new Group();
//            g2.setGroupName("administrator");
//            g2.setName("管理员");
//            g2.setType("可以查看所有模块");
            
            Group g3 = new Group();
            g3.setGroupName("salesperson");
            g3.setName("采购人员");
            g3.setType("可以查看采购模块");
            
            Group g4 = new Group();
            g4.setGroupName("procurementStaff");
            g4.setName("销售人员");
            g4.setType("可以查看订单模块、物流模块");
            
            Group g5 = new Group();
            g5.setGroupName("warehouseStaff");
            g5.setName("仓库人员");
            g5.setType("可以查看……？");
            
            //groupService.save(g1);
            groupService.save(g5);
            
            List<Group> entityLists = new ArrayList<Group>();
            //entityLists.add(g2);
            entityLists.add(g3);
            entityLists.add(g4);
            groupService.saveAll(entityLists);
            System.out.print("操作失败");
		}catch (Exception e) {
            e.printStackTrace();
            System.out.print("操作失败");
     }
	}

	@Test
	public void GroupFindAll() {
		try {
			List<GroupDTO> dto = new ArrayList<GroupDTO>();
			dto = groupService.findAll();
			System.out.println(dto.toString());
			for(int i=0; i<dto.size(); i++) {
				String groupName = dto.get(i).getName();
				System.out.println(groupName);
				
			}
			
		}catch (Exception e) {
			e.printStackTrace();
			System.out.print("失败！");
		}
	}
	
	@Test
	public void GroupOne() {
		try {
			Group entity = new Group();
			entity = groupService.findById("superAdministrator").get();
			System.out.println(entity.toString());
		}catch (Exception e) {
			e.printStackTrace();
			System.out.print("失败！");
		}
	}
}
