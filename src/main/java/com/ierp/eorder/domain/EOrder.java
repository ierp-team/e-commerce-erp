package com.ierp.eorder.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @author ghb, Date:Sep 26, 20183:34:16 PM
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
    private String orderStatus;
    //物流信息
    private String expressNumber;   
    private String logisticsCompany;
    private Float freight;
    private String logisticsInformation;
    
    private List<EOrderPoduct> orderProducts= new ArrayList<EOrderPoduct>();
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

    public Date getCreateTime() {
        return createTime;
    }

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

    public String getOrderStatus() {
        return orderStatus;
    }

    public String getExpressNumber() {
        return expressNumber;
    }

    public String getLogisticsCompany() {
        return logisticsCompany;
    }

    public Float getFreight() {
        return freight;
    }

    public String getLogisticsInformation() {
        return logisticsInformation;
    }
    @OneToMany(mappedBy="eOrder" ,cascade=CascadeType.ALL)  
    public List<EOrderPoduct> getOrderProducts() {
        return orderProducts;
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

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public void setExpressNumber(String expressNumber) {
        this.expressNumber = expressNumber;
    }

    public void setLogisticsCompany(String logisticsCompany) {
        this.logisticsCompany = logisticsCompany;
    }

    public void setFreight(Float freight) {
        this.freight = freight;
    }

    public void setLogisticsInformation(String logisticsInformation) {
        this.logisticsInformation = logisticsInformation;
    }

    public void setOrderProducts(List<EOrderPoduct> orderProducts) {
        this.orderProducts = orderProducts;
    }
    
}
