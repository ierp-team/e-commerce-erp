package com.ierp.permissionmodule.navigation.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ierp.common.web.SessionUtil;
import com.ierp.common.web.TreeNode;
import com.ierp.permissionmodule.group.domain.Group;
import com.ierp.permissionmodule.group.repository.GroupRepository;
import com.ierp.permissionmodule.navigation.domain.NavigationNode;
import com.ierp.permissionmodule.navigation.repository.NavigationNodeRepository;
@Service
@Transactional
public class NavigationNodeService implements INavigationNodeService {

    @Autowired
    NavigationNodeRepository navigationNodeRepository ;
    
    @Autowired
    GroupRepository groupRepository ;
    
    @Override
    public void save(NavigationNode entity) {
        navigationNodeRepository.save(entity);
    }

    @Override
    public void delete(NavigationNode entity) {
        navigationNodeRepository.delete(entity);
    }

    @Override
    public void delete(Long id) {
        navigationNodeRepository.deleteById(id);
    }

    @Override
    public void delete(Long[] ids) {
        for (Long id : ids) {
            navigationNodeRepository.deleteById(id);
        }
    }

    @Override
    public Optional<NavigationNode> findOne(Long id) {
        return navigationNodeRepository.findById(id);
    }

    @Override
    public List<NavigationNode> findAll() {
        
        return (List<NavigationNode>) navigationNodeRepository.findAll();
    }
    
    @Override
    public NavigationNode findByText(String text) {
        return navigationNodeRepository.findByText(text);
    }

    @Override
    public List<TreeNode> findChildrenNodes(Long parentId,HttpSession session) {
        List<TreeNode> nodeList = new ArrayList<TreeNode>();
        
        List<NavigationNode> lists;
        List<NavigationNode> displayLists = new ArrayList<NavigationNode>();
//        List<Group> groupLists = SessionUtil.getGroupList(session);
        String groupName = "";
        System.out.println(groupName);

        groupName=SessionUtil.getGroupNames(session);
        System.out.println(groupName);
        if(groupName ==null){
            System.out.println(groupName);
            displayLists.add(navigationNodeRepository.findByViewType("login"));
            System.out.println(navigationNodeRepository.findByViewType("login").getText());
        }else{
            Group group = groupRepository.findByGroupName(groupName);
            displayLists = group.getChildNodes();
            System.out.println(group.getName());
            System.out.println(displayLists.toString());
        }
        
        if(parentId==null) {
            lists =  navigationNodeRepository.findParentNodes();
            lists.retainAll(displayLists);
        }else {
            lists =  navigationNodeRepository.findChildNodes(parentId);
            lists.retainAll(displayLists);
        }
        
        for(NavigationNode tn : lists) {
            TreeNode node  = new TreeNode();
            
            node.setId(tn.getId());
            node.setText(tn.getText());
            node.setIconCls(tn.getIconCls());
//            node.setLeaf(tn.isLeaf());
            node.setRowCls(tn.getRowCls());
            node.setViewType(tn.getViewType());
            node.setReference(tn.getReference());

            
            if(tn.getChildNodes()!=null) {
                if(tn.getChildNodes().size()>0) {
                    node.setLeaf(false);//设置为父节点
                }else {
                    node.setLeaf(true);//设置为子节点
                }
            }
            nodeList.add(node);
        }
        return nodeList;
    }

    
}

