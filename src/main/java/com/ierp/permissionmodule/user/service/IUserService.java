package com.ierp.permissionmodule.user.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.ierp.permissionmodule.user.domain.User;
import com.ierp.permissionmodule.user.domain.UserDTO;

public interface IUserService {
	public User save(User entity);
	public List<User> saveAll(List<User> entities);
	
	public Optional<User> findById(String id);
	public List<UserDTO> findAll();
	public Page<UserDTO> findAll(Pageable pageable);
	public Page<User> findAll(Specification<User> spec, Pageable pageable);
}
