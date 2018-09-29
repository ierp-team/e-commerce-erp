package com.ierp.expressco.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * 快递公司类
 *
 */
@Entity
@Table(name="t_expressco")
public class Expressco {
	
	private Long expresscoId;			//物流公司id
	private String expresscoCode;		//物流公司编号
	private String expresscoName;		//物流公司名
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Long getExpresscoId() {
		return expresscoId;
	}
	@NotNull
	public String getExpresscoCode() {
		return expresscoCode;
	}
	@NotNull
	public String getExpresscoName() {
		return expresscoName;
	}
	
	public void setExpresscoId(Long expresscoId) {
		this.expresscoId = expresscoId;
	}
	public void setExpresscoCode(String expresscoCode) {
		this.expresscoCode = expresscoCode;
	}
	public void setExpresscoName(String expresscoName) {
		this.expresscoName = expresscoName;
	}
	
}
