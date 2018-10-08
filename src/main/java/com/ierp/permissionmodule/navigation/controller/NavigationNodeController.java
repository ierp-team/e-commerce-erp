package com.ierp.permissionmodule.navigation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ierp.common.web.ExtAjaxResponse;
import com.ierp.common.web.TreeNode;
//import com.ierp.common.web.TreeNode;
import com.ierp.permissionmodule.navigation.domain.NavigationNode;
import com.ierp.permissionmodule.navigation.service.NavigationNodeService;

@Controller
@RequestMapping("/navigation")
public class NavigationNodeController {
    @Autowired
    private NavigationNodeService navigationNodeService;

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
    public @ResponseBody List<TreeNode> findNodesByParentId(@RequestParam("node") String node)
    {
        if(node.equals("root")) {
            return navigationNodeService.findChildrenNodes(null);
        }else {
            return navigationNodeService.findChildrenNodes(Long.parseLong(node));
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
    
    @PostMapping("/delete")
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
    
    @RequestMapping("/init")
    public @ResponseBody ExtAjaxResponse initData() 
    { 
        try {
            NavigationNode nn1 = new NavigationNode();
            nn1.setText("登录模块");
            nn1.setIconCls("x-fa fa-balance-scale");
            nn1.setLeaf(false);
            nn1.setViewType("login");
            nn1.setRowCls("nav-tree-badge nav-tree-badge-hot");
            
//            NavigationNode nn2 = new NavigationNode();
//            nn2.setText("用户模块");
//            nn2.setIconCls("x-fa fa-balance-scale");
//            nn2.setLeaf(true);
//            nn2.setViewType("user");
//            nn2.setRowCls("nav-tree-badge nav-tree-badge-new");
//            
//            
//            NavigationNode nn11 = new NavigationNode();
//            nn11.setText("初始订单");
//            nn11.setIconCls("x-fa fa-balance-scale");
//            nn11.setLeaf(true);
//            nn11.setViewType("order");
//            nn11.setRowCls("nav-tree-badge nav-tree-badge-new");
//            
//            nn11.setParentNode(nn1);
//            
//            NavigationNode nn12 = new NavigationNode();
//            nn12.setText("已分配订单");
//            nn12.setIconCls("x-fa fa-balance-scale");
//            nn12.setLeaf(true);
//            nn12.setViewType("");
//            nn12.setRowCls("nav-tree-badge nav-tree-badge-new");
//            
//            nn12.setParentNode(nn1);
//            
//            NavigationNode nn13 = new NavigationNode();
//            nn13.setText("已配货订单");
//            nn13.setIconCls("x-fa fa-balance-scale");
//            nn13.setLeaf(true);
//            nn13.setViewType("order");
//            nn13.setRowCls("nav-tree-badge nav-tree-badge-new");
//            
//            nn13.setParentNode(nn1);
//            
//           
//            
//            nn1.getChildNodes().add(nn11);
//            nn1.getChildNodes().add(nn12);
//            nn1.getChildNodes().add(nn13);
//
               navigationNodeService.save(nn1);
//            navigationNodeService.save(nn2);
         
             return new ExtAjaxResponse(true,"操作成功！");
        } catch (Exception e) {
             e.printStackTrace();
             return new ExtAjaxResponse(false,"操作失败！");
        }
    } 
}
