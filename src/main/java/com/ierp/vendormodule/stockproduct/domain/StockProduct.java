package com.ierp.vendormodule.stockproduct.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.ierp.vendormodule.product.domain.Product;
import com.ierp.vendormodule.stockorder.domain.StockOrder;

@Entity
@Table(name="t_stockproduct")
public class StockProduct {
	private Long stockProductId;
	private StockOrder stockOrder;
	private Product product;
	private Integer stockProductQuan;
	private Float stockProductAmmount;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Long getStockProductId() {
		return stockProductId;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="stockOrderId")
	public StockOrder getStockOrder() {
		return stockOrder;
	}
	
	@OneToOne
	@JoinColumn(name="productId")
	public Product getProduct() {
		return product;
	}
	
	public Integer getStockProductQuan() {
		return stockProductQuan;
	}
	
	public Float getStockProductAmmount() {
//		stockProductAmmount = (float) 0;
//		stockProductAmmount = product.getProductPrice() * stockProductQuan;
		return stockProductAmmount;
	}
	
	public void setStockProductId(Long stockProductId) {
		this.stockProductId = stockProductId;
	}

	public void setStockOrder(StockOrder stockOrder) {
		this.stockOrder = stockOrder;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public void setStockProductQuan(Integer stockProductQuan) {
		this.stockProductQuan = stockProductQuan;
	}

	public void setStockProductAmmount(Float stockProductAmmount) {
		this.stockProductAmmount = stockProductAmmount;
	}
}
