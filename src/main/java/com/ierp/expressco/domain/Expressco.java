package com.ierp.expressco.domain;

import javax.persistence.Column;
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
	
	private Long id;			//物流公司id
	private String expresscoCode;		//物流公司编码：详细编码参考《2018快递鸟接口支持快递公司编码.xlsx》
	private String expresscoName;		//物流公司名
	
	
	@Id
	@Column(name="expressco_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}
	@NotNull
	public String getExpresscoCode() {
		return expresscoCode;
	}
	@NotNull
	public String getExpresscoName() {
		return expresscoName;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	public void setExpresscoCode(String expresscoCode) {
		this.expresscoCode = expresscoCode;
	}
	public void setExpresscoName(String expresscoName) {
		this.expresscoName = expresscoName;
	}
	
	
	@Override
	public String toString() {
		return "Expressco [id=" + id + ", expresscoCode=" + expresscoCode + ", expresscoName=" + expresscoName + "]";
	}
	
}
