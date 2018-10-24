package com.ierp.permissionmodule;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ierp.permissionmodule.group.domain.Group;
import com.ierp.permissionmodule.group.service.IGroupService;
import com.ierp.permissionmodule.navigation.domain.NavigationNode;
import com.ierp.permissionmodule.navigation.service.INavigationNodeService;


@RunWith(SpringRunner.class)
@SpringBootTest
public class NavigationServiceTest {

    @Autowired
    INavigationNodeService navigationNodeService;
    
    @Autowired
    IGroupService groupService;
    
    @Test
    public void initNavigations() {
        
        NavigationNode n000 = new NavigationNode();
        n000.setIconCls("x-fa fa-desktop");
        n000.setLeaf(true);
        n000.setText("首页");
        n000.setViewType("admindashboard");
        n000.setRowCls("");  
        
        
        NavigationNode n0 = new NavigationNode();
        n0.setText("点击或刷新登录");
        n0.setIconCls("x-fa fa-balance-scale");
        n0.setLeaf(true);
        n0.setViewType("login");
        n0.setRowCls("");
        
        NavigationNode n1 = new NavigationNode();
        n1.setIconCls("x-fa fa-first-order");
        n1.setLeaf(false);
        n1.setText("EOrder");
        n1.setViewType("eOrderPanel");
        n1.setRowCls("");
        
        
        
        NavigationNode n2 = new NavigationNode();
        n2.setText("初始订单");
        n2.setIconCls("x-fa fa-tasks");
        n2.setLeaf(true);
        n2.setViewType("originalOrderPanel");
        n2.setRowCls("nav-tree-badge nav-tree-badge-new");
        
        
        NavigationNode nn12 = new NavigationNode();
        nn12.setText("未匹配订单");
        nn12.setIconCls("x-fa fa-balance-scale");
        nn12.setLeaf(true);
        nn12.setViewType("noMatchOrderPanel");
        nn12.setRowCls("");
        
        
        NavigationNode n3 = new NavigationNode();
        n3.setText("已分配订单");
        n3.setIconCls("x-fa fa-bookmark");
        n3.setLeaf(true);
        n3.setViewType("assignedOrderPanel");
        n3.setRowCls("");
        
        NavigationNode n4 = new NavigationNode();
        n4.setText("未分配订单");
        n4.setIconCls("x-fa fa-bookmark-o");
        n4.setLeaf(true);
        n4.setViewType("noAssignOrderPanel");
        n4.setRowCls("");
        
        NavigationNode n5 = new NavigationNode();
        n5.setText("待发货订单");
        n5.setIconCls("x-fa fa-th-large");
        n5.setLeaf(true);
        n5.setViewType("noDeliverOrderPanel");
        n5.setRowCls("");
        
        NavigationNode n6 = new NavigationNode();
        n6.setText("已发货订单");
        n6.setIconCls("x-fa fa-truck");
        n6.setLeaf(true);
        n6.setViewType("deliveredOrderPanel");
        n6.setRowCls("nav-tree-badge nav-tree-badge-new");
        
        NavigationNode n7 = new NavigationNode();
        n7.setText("已收货订单");
        n7.setIconCls("x-fa fa-calendar-check-o");
        n7.setLeaf(true);
        n7.setViewType("completedOrderPanel");
        n7.setRowCls("");
        
        NavigationNode n8 = new NavigationNode();
        n8.setText("已取消订单");
        n8.setIconCls("x-fa fa-undo");
        n8.setLeaf(true);
        n8.setViewType("cancelOrderPanel");
        n8.setRowCls("");
        
        NavigationNode n9 = new NavigationNode();
        n9.setText("订单处理");
        n9.setIconCls("x-fa fa-thumb-tack");
        n9.setLeaf(true);
        n9.setViewType("eOrderManageMainPanel");
        n9.setRowCls("nav-tree-badge nav-tree-badge-new");
        
        NavigationNode n10 = new NavigationNode();
        n10.setText("流程定义");
        n10.setIconCls("x-fa fa-code-fork");
        n10.setLeaf(true);
        n10.setViewType("processDefinitionPanel");
        n10.setRowCls("");
        
      NavigationNode nn1 = new NavigationNode();
      nn1.setIconCls("x-fa fa-bars");
      nn1.setLeaf(true);
      nn1.setText("订单");
      nn1.setViewType("stockOrder");
      nn1.setRowCls("");
      
      NavigationNode nn2 = new NavigationNode();
      nn2.setIconCls("x-fa fa-truck");
      nn2.setLeaf(true);
      nn2.setText("供应商");
      nn2.setViewType("vendor");
      nn2.setRowCls("");
      
      NavigationNode nn3 = new NavigationNode();
      nn3.setIconCls("x-fa fa-product-hunt");
      nn3.setLeaf(true);
      nn3.setText("产品");
      nn3.setViewType("product");
      nn3.setRowCls("");
      
      NavigationNode nn4 = new NavigationNode();
      nn4.setIconCls("x-fa fa-product-hunt");
      nn4.setLeaf(true);
      nn4.setText("添加采购订单");
      nn4.setViewType("createOrder");
      nn4.setRowCls("");
      
      NavigationNode nn5 = new NavigationNode();
      nn5.setIconCls("x-fa fa-users");
      nn5.setLeaf(true);
      nn5.setText("员工管理");
      nn5.setViewType("user");
      nn5.setRowCls("");
      
      NavigationNode nn6 = new NavigationNode();
      nn6.setIconCls("x-fa fa-user-o");
      nn6.setLeaf(true);
      nn6.setText("角色管理");
      nn6.setViewType("group");
      nn6.setRowCls("");
      
      NavigationNode nn56 = new NavigationNode();
      nn56.setIconCls("x-fa fa-building");
      nn56.setLeaf(false);
      nn56.setText("公司管理");
      nn56.setViewType("");
      nn56.setRowCls("");
      
      NavigationNode nn7 = new NavigationNode();
      nn7.setIconCls("x-fa fa-envelope");
      nn7.setLeaf(true);
      nn7.setText("快递公司管理");
      nn7.setViewType("expressco");
      nn7.setRowCls("");
      
      NavigationNode nn8= new NavigationNode();
      nn8.setIconCls("x-fa fa-truck");
      nn8.setLeaf(true);
      nn8.setText("物流管理");
      nn8.setViewType("logistics");
      nn8.setRowCls("");
      
      NavigationNode nn9 = new NavigationNode();
      nn9.setIconCls("x-fa fa-cubes");
      nn9.setLeaf(true);
      nn9.setText("商品管理");
      nn9.setViewType("goods");
      nn9.setRowCls("");
      
      NavigationNode nn1234 = new NavigationNode();
      nn1234.setIconCls("x-fa fa-arrows");
      nn1234.setLeaf(false);
      nn1234.setText("供应商管理");
      nn1234.setViewType("");
      nn1234.setRowCls("");

      
      
    
      n1.getChildNodes().add(n3);
      n1.getChildNodes().add(n4);
      n1.getChildNodes().add(n5);
      n1.getChildNodes().add(n6);
      n1.getChildNodes().add(n7);
      n1.getChildNodes().add(n8);
      

      n3.setParentNode(n1);
      n4.setParentNode(n1);
      n5.setParentNode(n1);
      n6.setParentNode(n1);
      n7.setParentNode(n1);
      n8.setParentNode(n1);
      
      nn1234.getChildNodes().add(nn1);
      nn1234.getChildNodes().add(nn2);
      nn1234.getChildNodes().add(nn3);
      nn1234.getChildNodes().add(nn4);
      nn56.getChildNodes().add(nn5);
      nn56.getChildNodes().add(nn6);
      
      nn1.setParentNode(nn1234);
      nn2.setParentNode(nn1234);
      nn3.setParentNode(nn1234);
      nn4.setParentNode(nn1234);
      
      nn5.setParentNode(nn56);
      nn6.setParentNode(nn56);
      
      navigationNodeService.save(n000);
      navigationNodeService.save(n0);
      navigationNodeService.save(n1);
      navigationNodeService.save(n2);
      navigationNodeService.save(n3);
      navigationNodeService.save(n4);
      navigationNodeService.save(n5);
      navigationNodeService.save(n6);            
      navigationNodeService.save(n7);
      navigationNodeService.save(n8);           
      navigationNodeService.save(n9);
      navigationNodeService.save(n10);
      navigationNodeService.save(nn1);
      navigationNodeService.save(nn2);
      navigationNodeService.save(nn3);
      navigationNodeService.save(nn4);
      navigationNodeService.save(nn5);
      navigationNodeService.save(nn6);            
      navigationNodeService.save(nn7);
      navigationNodeService.save(nn8);           
      navigationNodeService.save(nn9);
      navigationNodeService.save(nn56);
      navigationNodeService.save(nn1234);
      
      Group g1  = groupService.findById("warehouseStaff").get();
      Group g2  = groupService.findById("salesperson").get();
      Group g3  = groupService.findById("procurementStaff").get();
      
      
      g1.getChildNodes().add(n000);
      g1.getChildNodes().add(n0);
      g1.getChildNodes().add(n1);
      g1.getChildNodes().add(n2);
      g1.getChildNodes().add(n3);
      g1.getChildNodes().add(n4);
      g1.getChildNodes().add(n5);
      g1.getChildNodes().add(n6);
      g1.getChildNodes().add(n7);
      g1.getChildNodes().add(n8);
      g1.getChildNodes().add(n9);
      g1.getChildNodes().add(nn1);
      g1.getChildNodes().add(nn2);
      g1.getChildNodes().add(nn3);
      g1.getChildNodes().add(nn4);
      g1.getChildNodes().add(nn7);
      g1.getChildNodes().add(nn8);
      g1.getChildNodes().add(nn9);
      g1.getChildNodes().add(nn1234);
      
      g2.getChildNodes().add(n000);
      g2.getChildNodes().add(n1);
      g2.getChildNodes().add(n2);
      g2.getChildNodes().add(n3);
      g2.getChildNodes().add(n4);
      g2.getChildNodes().add(n5);
      g2.getChildNodes().add(n6);
      g2.getChildNodes().add(n7);
      g2.getChildNodes().add(n8);
      g2.getChildNodes().add(n9);
      g2.getChildNodes().add(nn9);
      
      g3.getChildNodes().add(n000);
      g3.getChildNodes().add(n1);
      g3.getChildNodes().add(n2);
      g3.getChildNodes().add(n3);
      g3.getChildNodes().add(n4);
      g3.getChildNodes().add(n5);
      g3.getChildNodes().add(n6);
      g3.getChildNodes().add(n7);
      g3.getChildNodes().add(n8);
      g3.getChildNodes().add(n9);
      g3.getChildNodes().add(nn1);
      g3.getChildNodes().add(nn2);
      g3.getChildNodes().add(nn3);
      g3.getChildNodes().add(nn4);
      g3.getChildNodes().add(nn7);
      g3.getChildNodes().add(nn8);
      g3.getChildNodes().add(nn9);
      g3.getChildNodes().add(nn1234);


      Group group1  = groupService.findById("administrator").get();
      Group group2  = groupService.findById("superAdministrator").get();
      List<NavigationNode> navigationNodes = navigationNodeService.findAll();
    
      for(NavigationNode navigationNode :navigationNodes){
          navigationNode.setChecked(false);
          group1.getChildNodes().add(navigationNode);          
          group2.getChildNodes().add(navigationNode);
      }
      groupService.save(g1);
      groupService.save(g2);
      groupService.save(g3);
      groupService.save(group1);
      groupService.save(group1);
      
    }
    @Test
    public void change() {
        NavigationNode navigationNode = navigationNodeService.findByText("初始订单");
        navigationNode.setParentNode(null);
        NavigationNode navigationNode1 = navigationNodeService.findByText("EOrder");
        navigationNode1.getChildNodes().remove(0);
    }
}
