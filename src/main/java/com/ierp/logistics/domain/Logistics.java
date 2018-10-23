package com.ierp.logistics.domain;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.ierp.expressco.domain.Expressco;


/**
 * 物流信息类
 *
 */
@Entity
@Table(name="t_logistics")
public class Logistics {

	private Long id;			//物流id
	private Expressco logisticsCompany;			//快递公司
	private String expressNumber;			//物流单号
	private Float freight;			//运费
	
	//物流参数
	private String eorderCode;			//订单编码 OrderCode
	private Integer payType;			//邮费支付方式:1-现付，2-到付，3-月结，4-第三方支付(仅SF支持) PayType
	private String expType;			//快递类型：1-标准快件 ExpType
	private Integer isReturnSignBill;			//是否要求签回单 1-	要求 0-不要求 IsReturnSignBill
	private String isReturnPrintTemplate;			//返回电子面单模板：0-不需要；1-需要 IsReturnPrintTemplate
	
	//发件人信息
	private String senderEntireAddress;			//发件人地址
	private String senderContact;			//联系人（发件人）Sender.Name
	private String senderPhont;			//发件人号码 Sender.Mobile
	
	//客户（收件人）信息
	private String entireAddress;			//收件人地址
    private String contact;			//联系人（收件人）Receiver.Name
    private String phone;			//收件人手机号码 Receiver.Mobile
    
    //货物信息
	private String goodsType;       //商品名称 Commodity.GoodsName
	private Double weight;			//包裹总重量kg Weight
	
    
    @Id
	@Column(name="logistics_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    public Long getId() {
		return id;
	}
	@NotNull
	@ManyToOne
	public Expressco getLogisticsCompany() {
		return logisticsCompany;
	}
	public String getExpressNumber() {
		return expressNumber;
	}
	public Float getFreight() {
		return freight;
	}
	public String getEorderCode() {
		return eorderCode;
	}
	public Integer getPayType() {
		return payType;
	}
	public String getExpType() {
		return expType;
	}
	public Integer getIsReturnSignBill() {
		return isReturnSignBill;
	}
	public String getIsReturnPrintTemplate() {
		return isReturnPrintTemplate;
	}
	public String getSenderEntireAddress() {
		return senderEntireAddress;
	}
	public String getSenderContact() {
		return senderContact;
	}
	public String getSenderPhont() {
		return senderPhont;
	}
	public String getEntireAddress() {
		return entireAddress;
	}
	public String getContact() {
		return contact;
	}
	public String getPhone() {
		return phone;
	}
	public String getGoodsType() {
		return goodsType;
	}
	public Double getWeight() {
		return weight;
	}
	
	
	public void setId(Long id) {
		this.id = id;
	}
	public void setLogisticsCompany(Expressco logisticsCompany) {
		this.logisticsCompany = logisticsCompany;
	}
	public void setExpressNumber(String expressNumber) {
		this.expressNumber = expressNumber;
	}
	public void setFreight(Float freight) {
		this.freight = freight;
	}
	public void setEorderCode(String eorderCode) {
		this.eorderCode = eorderCode;
	}
	public void setPayType(Integer payType) {
		this.payType = payType;
	}
	public void setExpType(String expType) {
		this.expType = expType;
	}
	public void setIsReturnSignBill(Integer isReturnSignBill) {
		this.isReturnSignBill = isReturnSignBill;
	}
	public void setIsReturnPrintTemplate(String isReturnPrintTemplate) {
		this.isReturnPrintTemplate = isReturnPrintTemplate;
	}
	public void setSenderEntireAddress(String senderEntireAddress) {
		this.senderEntireAddress = senderEntireAddress;
	}
	public void setSenderContact(String senderContact) {
		this.senderContact = senderContact;
	}
	public void setSenderPhont(String senderPhont) {
		this.senderPhont = senderPhont;
	}
	public void setEntireAddress(String entireAddress) {
		this.entireAddress = entireAddress;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public void setGoodsType(String goodsType) {
		this.goodsType = goodsType;
	}
	public void setWeight(Double weight) {
		this.weight = weight;
	}
	
}
