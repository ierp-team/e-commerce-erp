package com.ierp.permissionmodule.navigation.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ierp.common.web.ExtAjaxResponse;
import com.ierp.common.web.TreeNode;
import com.ierp.permissionmodule.group.domain.Group;
import com.ierp.permissionmodule.group.service.IGroupService;
//import com.ierp.common.web.TreeNode;
import com.ierp.permissionmodule.navigation.domain.NavigationNode;
import com.ierp.permissionmodule.navigation.service.INavigationNodeService;
import com.ierp.permissionmodule.navigation.service.NavigationNodeService;

@Controller
@RequestMapping("/navigation")
public class NavigationNodeController {
    @Autowired
    private INavigationNodeService navigationNodeService;
    
    @Autowired
    private IGroupService groupService;

    @RequestMapping("/findAll")
    public @ResponseBody List<NavigationNode> findAll()
    {
        return navigationNodeService.findAll();
    }
    /**
     * 
     * @param node
     *      默认根节点：node=root
     *             子节点：node=node.id
     * @return
     */
    @RequestMapping("/findNodes")
    public @ResponseBody List<TreeNode> findNodesByParentId(@RequestParam("node") String node ,HttpSession session)
    {
        if(node.equals("root")) {
            return navigationNodeService.findChildrenNodes(null,session);
        }else {
            return navigationNodeService.findChildrenNodes(Long.parseLong(node),session);
        }
    }
    
    @PostMapping("/saveOrUpdate")
    public @ResponseBody ExtAjaxResponse saveOrUpdate(NavigationNode navigationNode)
    {
        try {
            navigationNodeService.save(navigationNode);
             return new ExtAjaxResponse(true,"操作成功！");
        } catch (Exception e) {
             e.printStackTrace();
             return new ExtAjaxResponse(false,"操作失败！");
        }
    }
    
    @PostMapping("/deletes")
    public @ResponseBody ExtAjaxResponse delete(Long[] ids)
    {
        try {
            navigationNodeService.delete(ids);
             return new ExtAjaxResponse(true,"操作成功！");
        } catch (Exception e) {
             e.printStackTrace();
             return new ExtAjaxResponse(false,"操作失败！");
        }
    }
    
    @PostMapping(value="/delete/{id}")
    public @ResponseBody ExtAjaxResponse deleteOne(@PathVariable("id") Long id)
    {
        try {
            navigationNodeService.delete(id);
             return new ExtAjaxResponse(true,"操作成功！");
        } catch (Exception e) {
             e.printStackTrace();
             return new ExtAjaxResponse(false,"操作失败！");
        }
    }
    
    @RequestMapping("/init")
    public @ResponseBody ExtAjaxResponse initData() 
    { 
        try {
                      
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
            nn56.setLeaf(true);
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
            nn1234.setLeaf(true);
            nn1234.setText("供应商管理");
            nn1234.setViewType("");
            nn1234.setRowCls("");
            
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
            
            Group group  = groupService.findById("administrator").get();
            System.out.println(group.toString());
            
            
            

            
//            NavigationNode nn1 = navigationNodeService.findByText("EOrder");
//            System.out.println(nn1.getViewType());
//            NavigationNode nn2 = navigationNodeService.findByText("订单处理");
//            System.out.println(nn2.getViewType());
//            NavigationNode nn3 = navigationNodeService.findByText("未发货订单");
//            System.out.println(nn3.getViewType());
//            
            group.getChildNodes().add(nn1);
            group.getChildNodes().add(nn2);
            group.getChildNodes().add(nn3);
            group.getChildNodes().add(nn4);
            group.getChildNodes().add(nn5);
            group.getChildNodes().add(nn6);
            group.getChildNodes().add(nn7);
            group.getChildNodes().add(nn8);
            group.getChildNodes().add(nn9);
            group.getChildNodes().add(nn56);
            group.getChildNodes().add(nn1234);

            
            groupService.save(group);
            
         
             return new ExtAjaxResponse(true,"操作成功！");
        } catch (Exception e) {
             e.printStackTrace();
             return new ExtAjaxResponse(false,"操作失败！");
        }
    } 
}
