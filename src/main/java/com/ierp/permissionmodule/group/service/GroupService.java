package com.ierp.permissionmodule.group.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ierp.permissionmodule.group.domain.Group;
import com.ierp.permissionmodule.group.domain.GroupDTO;
import com.ierp.permissionmodule.group.repository.GroupRepository;

@Service
@Transactional
public class GroupService implements IGroupService {
	@Autowired
	private GroupRepository groupRepository;

	@Override
	public Group save(Group entity) {
		// TODO Auto-generated method stub
		return groupRepository.save(entity);
	}

	@Override
	public List<Group> saveAll(List<Group> entities) {
		// TODO Auto-generated method stub
		return (List<Group>) groupRepository.saveAll(entities);
	}

	@Override
	@Transactional(readOnly=true)
	public Optional<Group> findById(String id) {
		// TODO Auto-generated method stub
		return groupRepository.findById(id);
	}

	@Override
	public List<GroupDTO> findAll() {
		// TODO Auto-generated method stub
		List<Group> entityLists = (List<Group>) groupRepository.findAll();
		List<GroupDTO> dtoLists = null;
		if(entityLists != null) {
			dtoLists = new ArrayList<GroupDTO>();
			for(Group entity : entityLists) {
				GroupDTO dto = new GroupDTO();
				GroupDTO.entityToDTO(entity, dto);
				dtoLists.add(dto);
			}
		}
		return dtoLists;
	}

	@Override
	public List<Group> findAll(List<String> ids) {
		// TODO Auto-generated method stub
		return (List<Group>)groupRepository.findAllById(ids);
	}

	@Override
	@Transactional(readOnly=true)
	public boolean existsById(String id) {
		// TODO Auto-generated method stub
		return groupRepository.existsById(id);
	}

	@Override
	@Transactional(readOnly=true)
	public long count() {
		// TODO Auto-generated method stub
		return groupRepository.count();
	}

	@Override
	public void deleteById(String id) {
		// TODO Auto-generated method stub
		groupRepository.deleteById(id);
	}

	@Override
	public void deleteAll(String[] ids) {
		// TODO Auto-generated method stub
		List<String> idsLists = new ArrayList<String>(Arrays.asList(ids));
		List<Group> groups = (List<Group>)groupRepository.findAllById(idsLists);
		if(groups != null) {
			groupRepository.deleteAll(groups);//改：应该是逻辑删除，不是物理删除
		}
	}

	@Override
	public void delete(Group entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(List<Group> entities) {
		// TODO Auto-generated method stub
		
	}

	@Override
	@Transactional(readOnly=true)
	public Page<Group> findAll(Specification<Group> spec, Pageable pageable) {
		// TODO Auto-generated method stub
		return groupRepository.findAll(spec,pageable);
	}






	

}
