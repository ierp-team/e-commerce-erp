package com.ierp.permissionmodule.user.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ierp.permissionmodule.user.domain.User;
import com.ierp.permissionmodule.user.domain.UserDTO;
import com.ierp.permissionmodule.user.repository.UserRepository;

@Service
@Transactional
public class UserService implements IUserService {
	@Autowired
	private UserRepository userRepository;

	@Override
	public List<UserDTO> findAll() {
		// TODO Auto-generated method stub
		List<User> entityLists = (List<User>) userRepository.findAll();
		List<UserDTO> dtoLists = null;
		if(entityLists != null) {
			dtoLists = new ArrayList<UserDTO>();
			for(User entity : entityLists) {
				UserDTO dto = new UserDTO();
				UserDTO.entityToDTO(entity, dto);
				dtoLists.add(dto);
			}
		}
		return dtoLists;
	}

	@Override
	public User save(User entity) {
		// TODO Auto-generated method stub
		return userRepository.save(entity);
	}

	@Override
	public List<User> saveAll(List<User> entities) {
		// TODO Auto-generated method stub
		return (List<User>) userRepository.saveAll(entities);
	}

	@Override
	@Transactional(readOnly=true)
	public Optional<User> findById(String id) {
		// TODO Auto-generated method stub
		return userRepository.findById(id);
	}

	@Override
	@Transactional(readOnly=true)
	public Page<User> findAll(Specification<User> spec, Pageable pageable) {
		// TODO Auto-generated method stub
		return userRepository.findAll(spec, pageable);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<UserDTO> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		Page<User> entityPage = userRepository.findAll(pageable);
		List<User> entityLists = entityPage.getContent();
		List<UserDTO> dtoLists = null;
		if(entityLists != null) {
			dtoLists = new ArrayList<UserDTO>();
			for(User entity : entityLists) {
				UserDTO dto = new UserDTO();
				UserDTO.entityToDTO(entity, dto);
				dtoLists.add(dto);
			}
		}
		return new PageImpl<UserDTO>(dtoLists,pageable,entityPage.getTotalElements());
	}

}
