package com.ierp.eordermodule.eorder.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ierp.eordermodule.util.EOrderStatus;
import com.ierp.expressco.domain.Expressco;

public class EOrderDTO {
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
    private String logisticsCompany;
    
    private Float freight;
    private String logisticsInformation;
    
    public static void entityToDto(EOrder entity,EOrderDTO dto ) {
        if(entity!=null){
            BeanUtils.copyProperties(entity, dto);
            Expressco expressco = entity.getLogisticsCompany();
            if(expressco != null){
                dto.setLogisticsCompany(expressco.getExpresscoName());
            }else{
                dto.setLogisticsCompany(null);
            }          
        }
    }
    //前到后：2.维护多个对象 的数据 以及 对象之间的关联关系 (创建关联、更新关联)
    public static void dtoToEntity(EOrderDTO dto ,EOrder entity) {
        BeanUtils.copyProperties(dto, entity); 
    }
 
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getOrderNumber() {
        return orderNumber;
    }
    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }
    public String getSalePlatform() {
        return salePlatform;
    }
    public void setSalePlatform(String salePlatform) {
        this.salePlatform = salePlatform;
    }
    
    @JsonFormat(pattern="yyyy/MM/dd HH:mm:ss",timezone="GMT+8")
    public Date getCreateTime() {
        return createTime;
    }
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    
    @JsonFormat(pattern="yyyy/MM/dd HH:mm:ss",timezone="GMT+8")
    public Date getOverTime() {
        return overTime;
    }
    public void setOverTime(Date overTime) {
        this.overTime = overTime;
    }
    public Float getTotalPrice() {
        return totalPrice;
    }
    public void setTotalPrice(Float totalPrice) {
        this.totalPrice = totalPrice;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getContact() {
        return contact;
    }
    public void setContact(String contact) {
        this.contact = contact;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public EOrderStatus getOrderStatus() {
        return orderStatus;
    }
    public void setOrderStatus(EOrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }
    public Date getDiliverTime() {
        return diliverTime;
    }
    public void setDiliverTime(Date diliverTime) {
        this.diliverTime = diliverTime;
    }
    public String getExpressNumber() {
        return expressNumber;
    }
    public void setExpressNumber(String expressNumber) {
        this.expressNumber = expressNumber;
    }
    public String getLogisticsCompany() {
        return logisticsCompany;
    }
    public void setLogisticsCompany(String logisticsCompany) {
        this.logisticsCompany = logisticsCompany;
    }
    public Float getFreight() {
        return freight;
    }
    public void setFreight(Float freight) {
        this.freight = freight;
    }
    public String getLogisticsInformation() {
        return logisticsInformation;
    }
    public void setLogisticsInformation(String logisticsInformation) {
        this.logisticsInformation = logisticsInformation;
    }
}
