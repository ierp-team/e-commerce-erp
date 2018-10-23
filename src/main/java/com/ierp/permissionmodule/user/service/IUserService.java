package com.ierp.permissionmodule.user.service;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.multipart.MultipartFile;

import com.ierp.permissionmodule.user.domain.User;
import com.ierp.permissionmodule.user.domain.UserDTO;

public interface IUserService {
	public User save(User entity);
	public void save(UserDTO dto);
	public List<User> saveAll(List<User> entities);
	
	public Optional<User> findById(String id);
	public UserDTO findOne(String id);
	public List<UserDTO> findAll();
	public Page<UserDTO> findByGroup(String groupId,Pageable pageable);
	public Page<UserDTO> findAll(Pageable pageable);
	public Page<UserDTO> findAll(Specification<User> spec, Pageable pageable);

	public void deleteById(String id);
	public void deleteAll(String[] ids);
	//上传图片
	public String uploadImgFile(MultipartFile file, HttpServletRequest request);
	//数据加密
	public byte[] eccrypt(String info);
}
