package com.ierp.eordermodule.eorderproduct.domain;

import org.springframework.beans.BeanUtils;

import com.ierp.eordermodule.eorder.domain.EOrder;
import com.ierp.eordermodule.util.EOrderProductStatus;
import com.ierp.goods.domain.Goods;

public class EOrderProductDTO {


    private Long id;
    private Integer quantity;
    private Float totalPrice;
    private EOrderProductStatus orderProductStatus;
    private String goodName;
    
    
    public static void entityToDto(EOrderProduct entity,EOrderProductDTO dto ) {
        if(entity!=null){
            BeanUtils.copyProperties(entity, dto);
            if(entity.getGood()!=null){
                dto.setGoodName(entity.getGood().getGoodsName());
            }
        }
    }
//    //前到后：2.维护多个对象 的数据 以及 对象之间的关联关系 (创建关联、更新关联)
//    public static void dtoToEntity(EOrderProductDTO dto ,EOrderProduct entity) {
//        BeanUtils.copyProperties(dto, entity);        
//    }
//    
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Integer getQuantity() {
        return quantity;
    }
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    public Float getTotalPrice() {
        return totalPrice;
    }
    public void setTotalPrice(Float totalPrice) {
        this.totalPrice = totalPrice;
    }
    public EOrderProductStatus getOrderProductStatus() {
        return orderProductStatus;
    }
    public void setOrderProductStatus(EOrderProductStatus orderProductStatus) {
        this.orderProductStatus = orderProductStatus;
    }
    public String getGoodName() {
        return goodName;
    }
    public void setGoodName(String goodName) {
        this.goodName = goodName;
    }
}
