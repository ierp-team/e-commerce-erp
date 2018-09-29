package com.ierp.eorder.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class EOrderDTO {
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
    private Date diliverTime;
    
    private String expressNumber;   
    private String logisticsCompany;
    private Float freight;
    private String logisticsInformation;    
    /**------------流程数据--------------**/
    /*任务*/
    private String taskId;
    private String taskName;
    private Date   taskCreateTime;
    private String assignee;
    private String taskDefinitionKey;
    /*流程实例*/
    private String processInstanceId;
    /*流程图定义*/
    private String processDefinitionId;
    private boolean suspended;
    private int version;
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
    public String getOrderStatus() {
        return orderStatus;
    }
    public void setOrderStatus(String orderStatus) {
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
    public String getTaskId() {
        return taskId;
    }
    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }
    public String getTaskName() {
        return taskName;
    }
    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }
    public Date getTaskCreateTime() {
        return taskCreateTime;
    }
    public void setTaskCreateTime(Date taskCreateTime) {
        this.taskCreateTime = taskCreateTime;
    }
    public String getAssignee() {
        return assignee;
    }
    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }
    public String getTaskDefinitionKey() {
        return taskDefinitionKey;
    }
    public void setTaskDefinitionKey(String taskDefinitionKey) {
        this.taskDefinitionKey = taskDefinitionKey;
    }
    public String getProcessInstanceId() {
        return processInstanceId;
    }
    public void setProcessInstanceId(String processInstanceId) {
        this.processInstanceId = processInstanceId;
    }
    public String getProcessDefinitionId() {
        return processDefinitionId;
    }
    public void setProcessDefinitionId(String processDefinitionId) {
        this.processDefinitionId = processDefinitionId;
    }
    public boolean isSuspended() {
        return suspended;
    }
    public void setSuspended(boolean suspended) {
        this.suspended = suspended;
    }
    public int getVersion() {
        return version;
    }
    public void setVersion(int version) {
        this.version = version;
    }
}
