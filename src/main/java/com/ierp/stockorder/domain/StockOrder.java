package com.ierp.stockorder.domain;

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

import org.springframework.data.annotation.CreatedDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ierp.stockproduct.domain.StockProduct;

@Entity
@Table(name="t_stockorder")
public class StockOrder {
	private Long stockOrderId;
	private Date createTime;
	private String stockOrderNumber;
	private StockOrderStatus stockOrderStatus;
	private Float stockOrderSum;
	private String userName;
	private String address;
	private String phoneNumber;
	private List<StockProduct> stockProduct = new ArrayList<StockProduct>();
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Long getStockOrderId() {
		return stockOrderId;
	}
	
	@CreatedDate
	@JsonFormat(pattern="yyyy/MM/dd HH:mm:ss",timezone="GMT+8")
	public Date getCreateTime() {
		return createTime;
	}
	
	public String getStockOrderNumber() {
		return stockOrderNumber;
	}
	
	public StockOrderStatus getStockOrderStatus() {
		return stockOrderStatus;
	}
	
	public Float getStockOrderSum() {
		stockOrderSum = (float) 0;
		for (int i = 0; i < stockProduct.size(); i++) {
			stockOrderSum += stockProduct.get(i).getStockProductAmmount();
		}
		return stockOrderSum;
	}
	
	public String getUserName() {
		return userName;
	}

	public String getAddress() {
		return address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	@JsonIgnore
	@OneToMany(mappedBy = "stockOrder", cascade = CascadeType.ALL)
	public List<StockProduct> getStockProduct() {
		return stockProduct;
	}

	public void setStockOrderId(Long stockOrderId) {
		this.stockOrderId = stockOrderId;
	}
	
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public void setStockOrderNumber(String stockOrderNumber) {
		this.stockOrderNumber = stockOrderNumber;
	}
	
	public void setStockOrderStatus(StockOrderStatus stockOrderStatus) {
		this.stockOrderStatus = stockOrderStatus;
	}
	
	public void setStockOrderSum(Float stockOrderSum) {
		this.stockOrderSum = stockOrderSum;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public void setStockProduct(List<StockProduct> stockProduct) {
		this.stockProduct = stockProduct;
	}
}
