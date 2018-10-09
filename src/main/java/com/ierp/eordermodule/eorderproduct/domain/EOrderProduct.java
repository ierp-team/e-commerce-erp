package com.ierp.eordermodule.eorderproduct.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GeneratorType;

import com.ierp.eordermodule.eorder.domain.EOrder;
import com.ierp.eordermodule.util.EOrderProductStatus;
import com.ierp.goods.domain.Goods;

/**
 * @author ghb, Date:Sep 26, 20183:58:56 PM
 *
 */
@Entity
@Table(name="t_eorder_product")
public class EOrderProduct {
    private Long id;
    private Integer quantity;
    private Float totalPrice;
    
    private EOrderProductStatus orderProductStatus;

    private EOrder eOrder;
    private Goods good;
    //getters
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }
    public Integer getQuantity() {
        return quantity;
    }
    public Float getTotalPrice() {
        return totalPrice;
    }
    
    @ManyToOne(cascade=CascadeType.ALL)
    public EOrder geteOrder() {
        return eOrder;
    }
    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "goodsCode",referencedColumnName="goodsCode", insertable = false, updatable = false, unique = true)
    public Goods getGood() {
        return good;
    }
    public EOrderProductStatus getOrderProductStatus() {
        return orderProductStatus;
    }
    
    //setters
    public void setId(Long id) {
        this.id = id;
    }
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    public void setTotalPrice(Float totalPrice) {
        this.totalPrice = totalPrice;
    }
    public void seteOrder(EOrder eOrder) {
        this.eOrder = eOrder;
    }
    public void setGood(Goods good) {
        this.good = good;
    }
    public void setOrderProductStatus(EOrderProductStatus orderProductStatus) {
        this.orderProductStatus = orderProductStatus;
    }
}
