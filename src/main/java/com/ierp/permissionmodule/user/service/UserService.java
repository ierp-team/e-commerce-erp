package com.ierp.permissionmodule.user.service;

import java.io.File;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.ierp.permissionmodule.group.domain.Group;
import com.ierp.permissionmodule.group.repository.GroupRepository;
import com.ierp.permissionmodule.user.domain.User;
import com.ierp.permissionmodule.user.domain.UserDTO;
import com.ierp.permissionmodule.user.repository.UserRepository;

@Service
@Transactional
public class UserService implements IUserService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private GroupRepository groupRepository;

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
	public Page<UserDTO> findAll(Specification<User> spec, Pageable pageable) {
		// TODO Auto-generated method stub
		Page<User> entityPage = null;
		entityPage = userRepository.findAll(spec, pageable);
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
	
	@Override
	public void save(UserDTO dto) {
		// TODO Auto-generated method stub
		User entity = new User();
		UserDTO.dtoToEntity(dto, entity);
		if(dto.getRole() != null) {
			for(int i=0; i<dto.getRole().size();i++) {
				String groupId = dto.getRole().get(i);
				Optional<Group> group = groupRepository.findById(groupId);
				if(group.isPresent()) {
					entity.getGroup().add(group.get());
				}
			}
		}
		userRepository.save(entity);
	}
	
	@Override
	public UserDTO findOne(String id) {
		// TODO Auto-generated method stub
		Optional<User> entity = userRepository.findById(id);
		if(entity.isPresent()) {
			UserDTO dto = new UserDTO();
			UserDTO.entityToDTO(entity.get(), dto);
			return dto;
		}else {
			return null;
		}
	}
	
	@Override
	public String uploadImgFile(MultipartFile file, HttpServletRequest request) {
		// TODO Auto-generated method stub
		if(file.isEmpty()) {
			return "未选择文件！";
		}else if(file.getSize() > 5242880) {
			return "图片大小不能超过5M！";
		}else {
			String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf('.'));//后缀名
			if(!suffix.equals(".png") && !suffix.equals(".jpg") && !suffix.equals(".jpeg")) {
				return "格式错误！只支持jgp或png文件！";
			}
			// E:\Homework\personal\2018-2019-1\project\e-commerce-erp\src\main\webapp\resources\images\ user-profile\
			String uploadPath = request.getSession().getServletContext().getRealPath("/")+"resources"+File.separator+"images"+File.separator+"user-profile"+File.separator;//File.separator路径分割符
			File path = new File(uploadPath);
			if(!path.exists()) {
				path.mkdirs();// 创建文件夹
			}
			try {
				// UUID(Universally Unique Identifier)全局唯一标识符,
				String newFileName = UUID.randomUUID().toString().replace("-", "") + suffix;
				File serverFile = new File(path,newFileName); //服务器端保存文件对象
				file.transferTo(serverFile); //将上传的文件写入服务器端文件内
				return newFileName;
			}catch (Exception e) {
				e.printStackTrace();
				return "上传失败！";
			}
		}
	}

	@Override
	public void deleteById(String id) {
		// TODO Auto-generated method stub
		userRepository.deleteById(id);
	}

	@Override
	public void deleteAll(String[] ids) {
		// TODO Auto-generated method stub
		List<String> idLists = new ArrayList<String>(Arrays.asList(ids));
		List<User> users = (List<User>)userRepository.findAllById(idLists);
		if(users != null) {
			userRepository.deleteAll(users);
		}
	}

	
	@Override
	public Page<UserDTO> findByGroup(String groupId,Pageable pageable) {
		// TODO Auto-generated method stub
//		List<Object> objectLists = userRepository.findByGroup(groupId);
////		System.out.println("objectLists:"+objectLists);
//		List<UserDTO> dtoLists = new ArrayList<UserDTO>();
//		if(objectLists != null) {
//			for(int i=0; i<objectLists.size(); i++) {
////				System.out.println("objectLists.get(i):"+objectLists.get(i));
//				Object[] obj = (Object[])objectLists.get(i);
//				for(int j=0; j<obj.length; j++) {
////					System.out.println(j+"j:"+obj[j]);
//					UserDTO dto = new UserDTO();
//					if(j == 0) {
//						User user = (User) obj[j];
//						UserDTO.entityToDTO(user, dto);
//						System.out.println("user:"+i+user);
//						dtoLists.add(dto);
//					}
//				}
//			}
//		}
//		System.out.println(dtoLists);
		
		List<String> userId = userRepository.findByGroup(groupId);
//		System.out.println(userId);
		List<UserDTO> dtoLists = new ArrayList<UserDTO>();
		for(int i=0; i<userId.size(); i++) {
			Optional<User> entity = userRepository.findById(userId.get(i));
			if(entity.isPresent()) {
				UserDTO dto = new UserDTO();
				UserDTO.entityToDTO(entity.get(), dto);
				dtoLists.add(dto);
			}
		}
		return new PageImpl<UserDTO>(dtoLists,pageable,userId.size());
	}

	@Override
	public byte[] eccrypt(String info) {
		// TODO Auto-generated method stub
		//根据MD5算法生成MessageDigest对象
			MessageDigest md5;
			try {
				md5 = MessageDigest.getInstance("MD5");
				byte[] srcBytes = info.getBytes();
				//使用srcBytes更新摘要
				md5.update(srcBytes);
				//完成哈希算法，得到result
				byte[] resultBytes = md5.digest();
				return resultBytes;
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
	}

	

}
