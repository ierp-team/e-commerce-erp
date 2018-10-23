package com.ierp.permissionmodule.group.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

public class GroupQueryDTO {
	private String groupName;
	private String type;
	
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	@SuppressWarnings({"serial"})
	public static Specification<Group> getWhereClause(final GroupQueryDTO groupQueryDTO){
		return new Specification<Group>() {

			@Override
			public Predicate toPredicate(Root<Group> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				// TODO Auto-generated method stub
				List<Predicate> predicate = new ArrayList<>();
				if(StringUtils.isNotBlank(groupQueryDTO.getGroupName())) {
					predicate.add(criteriaBuilder.like(root.get("groupName").as(String.class), "%"+groupQueryDTO.getGroupName()+"%"));
				}
				if(StringUtils.isNotBlank(groupQueryDTO.getType())) {
					predicate.add(criteriaBuilder.like(root.get("type").as(String.class), "%"+groupQueryDTO.getGroupName()+"%"));
				}
				Predicate[] pre = new Predicate[predicate.size()];
				return query.where(predicate.toArray(pre)).getRestriction();
			}
			
		};
		
	}
	
}
