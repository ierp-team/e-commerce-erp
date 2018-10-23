package com.ierp.permissionmodule.navigation.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="t_navigation",uniqueConstraints = {@UniqueConstraint(columnNames={"text", "view_type"})})
public class NavigationNode 
{
    


    private Long id;
    private String text;
    private String iconCls;
    private String rowCls;
    private String viewType;
    private boolean leaf;
    private String reference;
    private String nodeId;
    
    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    //其他属性  
    private NavigationNode parentNode;//多个子节点  对  一个父节点
    private List<NavigationNode> childNodes = new ArrayList<NavigationNode>(); //一个节点  对  多个子节点

    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    @Column(name = "text")
    public String getText() {
        return text;
    }


    public String getIconCls() {
        return iconCls;
    }


    public String getRowCls() {
        return rowCls;
    }

    @Column(name = "view_type")
    public String getViewType() {
        return viewType;
    }


    public boolean isLeaf() {
        return leaf;
    }


    @ManyToOne(cascade=CascadeType.ALL)
    public NavigationNode getParentNode() {
        return parentNode;
    }
    @OneToMany(mappedBy="parentNode" ,cascade=CascadeType.ALL)
    public List<NavigationNode> getChildNodes() {
        return childNodes;
    }
    public String getReference() {
        return reference;
    }

  //setters


    public void setId(Long id) {
        this.id = id;
    }


    public void setText(String text) {
        this.text = text;
    }


    public void setIconCls(String iconCls) {
        this.iconCls = iconCls;
    }


    public void setRowCls(String rowCls) {
        this.rowCls = rowCls;
    }


    public void setViewType(String viewType) {
        this.viewType = viewType;
    }


    public void setLeaf(boolean leaf) {
        this.leaf = leaf;
    }


    public void setParentNode(NavigationNode parentNode) {
        this.parentNode = parentNode;
    }


    public void setChildNodes(List<NavigationNode> childNodes) {
        this.childNodes = childNodes;
    }
    
    public void setReference(String reference) {
        this.reference = reference;
    }
    
    
    
    
    
    

}

