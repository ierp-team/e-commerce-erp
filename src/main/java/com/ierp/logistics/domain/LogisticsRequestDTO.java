package com.ierp.logistics.domain;

import java.util.Date;

import com.ierp.common.beans.BeanUtils;
import com.ierp.expressco.domain.Expressco;

/**
 * 物流信息请求DTO
 *
 */
public class LogisticsRequestDTO {

	//注解后面的字段是本字段在快递鸟电子面单Api所需参数中对应的字段
	//物流参数
	private String eorderCode;			//订单编码 OrderCode
	private String expresscoCode;			//快递公司 ShipperCode
	private Integer payType;			//邮费支付方式:1-现付，2-到付，3-月结，4-第三方支付(仅SF支持) PayType
	private String expType;			//快递类型：1-标准快件 ExpType
	private Integer isReturnSignBill;			//是否要求签回单 1-	要求 0-不要求 IsReturnSignBill
	private String isReturnPrintTemplate;			//返回电子面单模板：0-不需要；1-需要 IsReturnPrintTemplate
	
	//发件人信息
	private String senderProvinceName;			//发件省 Sender.ProvinceName
    private String senderCityName;			//发件市 Sender.CityName
    private String senderExpAreaName;			//发件区/县 Sender.ExpAreaName
	private String senderAddress;			//发件人详细地址 Sender.Address
    private String senderContact;			//联系人（发件人）Sender.Name
    private String senderPhone;			//发件人号码 Sender.Tel
	
	//客户（收件人）信息
    private String provinceName;			//收件省 Receiver.ProvinceName
    private String cityName;			//收件市 Receiver.CityName
    private String expAreaName;			//收件区/县 Receiver.ExpAreaName
	private String address;			//收件人详细地址 Receiver.Address
    private String contact;			//联系人（收件人）Receiver.Name
    private String phone;			//收件人手机号码 Receiver.Mobile
    
    //货物信息
	private String goodsType;       //商品名称 Commodity.GoodsName
	private Double weight;			//包裹总重量kg Weight

	/* -------------------------dtoToEntity------------------------------- */
	public static void dtoToEntity(LogisticsRequestDTO dto, Logistics entity) {
		BeanUtils.copyProperties(dto, entity);
	}
	
	public String getSenderEntireAddress() {
		return senderProvinceName + senderCityName + senderExpAreaName + senderAddress;
	}
	public String getEntireAddress() {
		return provinceName + cityName + expAreaName + address;
	}
	public Float getFreight() {
		float freight = weight.intValue()*1 + 5;
		if (weight.intValue()==weight && weight!=0) {
			freight = freight-1;
		}
		return freight;
	}
	/* ------------------------------------------------------------------- */
	
	public String getEorderCode() {
		return eorderCode;
	}
	public String getExpresscoCode() {
		return expresscoCode;
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
	public String getSenderProvinceName() {
		return senderProvinceName;
	}
	public String getSenderCityName() {
		return senderCityName;
	}
	public String getSenderExpAreaName() {
		return senderExpAreaName;
	}
	public String getSenderAddress() {
		return senderAddress;
	}
	public String getSenderContact() {
		return senderContact;
	}
	public String getSenderPhone() {
		return senderPhone;
	}
	public String getProvinceName() {
		return provinceName;
	}
	public String getCityName() {
		return cityName;
	}
	public String getExpAreaName() {
		return expAreaName;
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
	public String getGoodsType() {
		return goodsType;
	}
	public Double getWeight() {
		return weight;
	}
	
	
	public void setEorderCode(String eorderCode) {
		this.eorderCode = eorderCode;
	}
	public void setExpresscoCode(String expresscoCode) {
		this.expresscoCode = expresscoCode;
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
	public void setSenderProvinceName(String senderProvinceName) {
		this.senderProvinceName = senderProvinceName;
	}
	public void setSenderCityName(String senderCityName) {
		this.senderCityName = senderCityName;
	}
	public void setSenderExpAreaName(String senderExpAreaName) {
		this.senderExpAreaName = senderExpAreaName;
	}
	public void setSenderAddress(String senderAddress) {
		this.senderAddress = senderAddress;
	}
	public void setSenderContact(String senderContact) {
		this.senderContact = senderContact;
	}
	public void setSenderPhone(String senderPhone) {
		this.senderPhone = senderPhone;
	}
	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public void setExpAreaName(String expAreaName) {
		this.expAreaName = expAreaName;
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
	public void setGoodsType(String goodsType) {
		this.goodsType = goodsType;
	}
	public void setWeight(Double weight) {
		this.weight = weight;
	}
	
}
