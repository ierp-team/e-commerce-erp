package com.ierp.permissionmodule.navigation.service;

import java.util.List;
import java.util.Optional;

import com.ierp.common.web.TreeNode;
import com.ierp.permissionmodule.navigation.domain.NavigationNode;

public interface INavigationNodeService {
  //增加（修改）、删除、
    public void save(NavigationNode entity);
    public void delete(NavigationNode entity);
    public void delete(Long id);
    public void delete(Long[] ids);
    //通用查询
    public Optional<NavigationNode> findOne(Long id);
    public List<NavigationNode> findAll();
    
    //自定义查询：
    public List<TreeNode> findChildrenNodes(Long parentId);
}
