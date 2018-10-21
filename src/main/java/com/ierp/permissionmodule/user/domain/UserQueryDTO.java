package com.ierp.permissionmodule.user.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.format.annotation.DateTimeFormat;



public class UserQueryDTO {
	private String userName;
	private String user_num;
//	private List<String> role = new ArrayList<String>();
	private String sex;
	private String status;
	@DateTimeFormat(pattern="yyyy/MM/dd")
//	@JsonFormat(pattern="yyyy/MM/dd")
	private Date birthdayFrom;
	@DateTimeFormat(pattern="yyyy/MM/dd")
//	@JsonFormat(pattern="yyyy/MM/dd")
	private Date birthdayTo;
	
	private String groupName;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUser_num() {
		return user_num;
	}
	public void setUser_num(String user_num) {
		this.user_num = user_num;
	}	
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getBirthdayFrom() {
		return birthdayFrom;
	}
	public void setBirthdayFrom(Date birthdayFrom) {
		this.birthdayFrom = birthdayFrom;
	}
	public Date getBirthdayTo() {
		return birthdayTo;
	}
	public void setBirthdayTo(Date birthdayTo) {
		this.birthdayTo = birthdayTo;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	
	
	public UserQueryDTO(String userName, String user_num, String sex, String status, Date birthdayFrom, Date birthdayTo,
			String groupName) {
		super();
		this.userName = userName;
		this.user_num = user_num;
		this.sex = sex;
		this.status = status;
		this.birthdayFrom = birthdayFrom;
		this.birthdayTo = birthdayTo;
		this.groupName = groupName;
	}
	public UserQueryDTO() {
		super();
	}
	
	
	/*动态生成where语句*/
	@SuppressWarnings("serial")
	public static Specification<User> getWhereClause(final UserQueryDTO userQueryDTO){
		return new Specification<User>() {

			@Override
			public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				// TODO Auto-generated method stub
				//1.声明Predicate集合
				List<Predicate> predicate = new ArrayList<>();
				
				//2.根据userQueryDTO查询条件动态添加Predicate
				if(StringUtils.isNotBlank(userQueryDTO.getUserName())) {
					predicate.add(criteriaBuilder.like(root.get("userName").as(String.class), "%"+userQueryDTO.getUserName()+"%"));
				}
				if(StringUtils.isNotBlank(userQueryDTO.getUser_num())) {
					predicate.add(criteriaBuilder.like(root.get("user_num").as(String.class), "%"+userQueryDTO.getUser_num()+"%"));
				}
				if(StringUtils.isNotBlank(userQueryDTO.getSex())) {
					predicate.add(criteriaBuilder.like(root.get("sex").as(String.class), "%"+userQueryDTO.getSex()+"%"));
				}
				if(StringUtils.isNotBlank(userQueryDTO.getStatus())) {
					predicate.add(criteriaBuilder.like(root.get("status").as(String.class), "%"+userQueryDTO.getStatus()+"%"));
				}
				if(null != userQueryDTO.getBirthdayFrom()) {
					predicate.add(criteriaBuilder.greaterThanOrEqualTo(root.get("birthday").as(Date.class), userQueryDTO.getBirthdayFrom()));
				}
				if(null != userQueryDTO.getBirthdayTo()) {
					predicate.add(criteriaBuilder.lessThanOrEqualTo(root.get("birthday").as(Date.class), userQueryDTO.getBirthdayTo()));
				}
				//3.根据Predicate集合生成并返回and 连接的 where条件
				Predicate[] pre = new Predicate[predicate.size()];
				return query.where(predicate.toArray(pre)).getRestriction();
			}
			
		};
		
	}
}
