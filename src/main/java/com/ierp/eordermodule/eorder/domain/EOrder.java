package com.ierp.eordermodule.eorder.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ierp.eordermodule.eorderproduct.domain.EOrderProduct;
import com.ierp.eordermodule.util.EOrderStatus;
import com.ierp.expressco.domain.Expressco;

/**
 * @author ghb, Date:Sep 26, 2018  3:34:16 PM
 *
 */
@Entity
@Table(name="t_eorder")
public class EOrder {
    //基本信息
    private Long id;
    private String orderNumber;
    private String salePlatform;
    private Date createTime;
    private Date overTime;    
    private Float totalPrice;
   
    //客户信息
    private String address;
    private String contact;
    private String phone;
    //订单状态
    private EOrderStatus orderStatus;
    //物流信息
    private Date diliverTime;
    private String expressNumber;   
    private Expressco logisticsCompany;
    
    private Float freight;
    private String logisticsInformation;
    
    private List<EOrderProduct> orderProducts= new ArrayList<EOrderProduct>();
    
    
    private String userId;//启动流程的用户ID
    private String processInstanceId;//流程实例Id：用于关联流程引擎相关数据,没有启动流程之前为""
    
    //getters
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public String getSalePlatform() {
        return salePlatform;
    }
    
    @JsonFormat(pattern="yyyy/MM/dd HH:mm:ss",timezone="GMT+8")
    public Date getCreateTime() {
        return createTime;
    }

    @JsonFormat(pattern="yyyy/MM/dd HH:mm:ss",timezone="GMT+8")
    public Date getOverTime() {
        return overTime;
    }

    public Float getTotalPrice() {
        return totalPrice;
    }

    public String getAddress() {
        return address;
    }

    public String getContact() {
        return contact;
    }

    public String getPhone() {
        return phone;
    }

    public EOrderStatus getOrderStatus() {
        return orderStatus;
    }
    @JsonFormat(pattern="yyyy/MM/dd HH:mm:ss",timezone="GMT+8")
    public Date getDiliverTime() {
        return diliverTime;
    }
    
    public String getExpressNumber() {
        return expressNumber;
    }
    @ManyToOne(cascade=CascadeType.ALL)
    public Expressco getLogisticsCompany() {
        return logisticsCompany;
    }

    public Float getFreight() {
        return freight;
    }

    public String getLogisticsInformation() {
        return logisticsInformation;
    }
    @OneToMany(mappedBy="eOrder" ,cascade=CascadeType.ALL)  
    public List<EOrderProduct> getOrderProducts() {
        return orderProducts;
    }
    public String getUserId() {
        return userId;
    }
    public String getProcessInstanceId() {
        return processInstanceId;
    }
    //setters

    public void setId(Long id) {
        this.id = id;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public void setSalePlatform(String salePlatform) {
        this.salePlatform = salePlatform;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public void setOverTime(Date overTime) {
        this.overTime = overTime;
    }

    public void setTotalPrice(Float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setOrderStatus(EOrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }
    
    public void setDiliverTime(Date diliverTime) {
        this.diliverTime = diliverTime;
    }

    public void setExpressNumber(String expressNumber) {
        this.expressNumber = expressNumber;
    }

    public void setLogisticsCompany(Expressco logisticsCompany) {
        this.logisticsCompany = logisticsCompany;
    }
    public void setFreight(Float freight) {
        this.freight = freight;
    }

    public void setLogisticsInformation(String logisticsInformation) {
        this.logisticsInformation = logisticsInformation;
    }

    public void setOrderProducts(List<EOrderProduct> orderProducts) {
        this.orderProducts = orderProducts;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public void setProcessInstanceId(String processInstanceId) {
        this.processInstanceId = processInstanceId;
    }

}
