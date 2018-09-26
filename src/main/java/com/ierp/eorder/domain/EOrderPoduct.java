package com.ierp.eorder.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GeneratorType;

/**
 * @author ghb, Date:Sep 26, 20183:58:56 PM
 *
 */
@Entity
@Table(name="t_eorder_product")
public class EOrderPoduct {
    private Long id;
    private Integer quantity;
    private Float totalPrice;
    private EOrder eOrder;
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
    
}
