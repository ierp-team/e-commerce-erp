package com.ierp.permissionmodule.group.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.ierp.permissionmodule.group.domain.Group;
import com.ierp.permissionmodule.group.domain.GroupDTO;
import com.ierp.permissionmodule.user.domain.UserDTO;

public interface IGroupService {
	public Group save(Group entity);
	public void save(GroupDTO dto);
	public List<Group> saveAll(List<Group> entities);
	
	public Optional<Group> findById(String id);
	
	public List<GroupDTO> findAll();
	public List<Group> findAll(List<String> ids);
	
	public boolean existsById(String id);
	public long count();
	
	
	public void deleteById(String id);
	public void deleteAll(String[] ids);
	public void delete(Group entity);
	public void delete(List<Group> entities);
	
	public Page<GroupDTO> findAll(Pageable pageable);
	public Page<Group> findAll(Specification<Group> spec, Pageable pageable);
	
	
}
