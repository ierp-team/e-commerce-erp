package com.ierp.permissionmodule.navigation.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ierp.common.web.TreeNode;
import com.ierp.permissionmodule.navigation.domain.NavigationNode;
import com.ierp.permissionmodule.navigation.repository.NavigationNodeRepository;
@Service
@Transactional
public class NavigationNodeService implements INavigationNodeService {

    @Autowired
    NavigationNodeRepository navigationNodeRepository ;
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
    public List<TreeNode> findChildrenNodes(Long parentId) {
        List<TreeNode> nodeList = new ArrayList<TreeNode>();
        
        List<NavigationNode> lists;
        if(parentId==null) {
            lists =  navigationNodeRepository.findParentNodes();
        }else {
            lists =  navigationNodeRepository.findChildNodes(parentId);
        }
        
        for(NavigationNode tn : lists) {
            TreeNode node  = new TreeNode();
            
            node.setId(tn.getId());
            node.setText(tn.getText());
            node.setIconCls(tn.getIconCls());
//            node.setLeaf(tn.isLeaf());
            node.setRowCls(tn.getRowCls());
            node.setViewType(tn.getViewType());
            
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

