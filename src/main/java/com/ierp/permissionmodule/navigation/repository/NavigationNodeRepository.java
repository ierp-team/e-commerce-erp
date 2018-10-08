package com.ierp.permissionmodule.navigation.repository;
import java.util.List;

import javax.validation.constraints.Null;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ierp.permissionmodule.navigation.domain.NavigationNode;

@Repository
public interface NavigationNodeRepository extends CrudRepository<NavigationNode, Long>
{
    /**
     * 查询根节点
     *      parentNode.id = null时 默认返回根节点
     * @return 节点集合 List<NavigationNode>
     */
    @Query("from NavigationNode c where c.parentNode.id = null")
    public List<NavigationNode> findParentNodes();
    
    /**
     * 根据父节点ID查询出子节点
     * @param parentId
     *      parentId != null时 默认返回子节点
     * @return 节点集合 List<NavigationNode>
     */
    @Query("from NavigationNode c where c.parentNode.id = ?1")
    public List<NavigationNode> findChildNodes(Long parentId);//null
}
