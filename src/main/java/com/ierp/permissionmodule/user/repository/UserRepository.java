package com.ierp.permissionmodule.user.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.ierp.permissionmodule.user.domain.User;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, String>,JpaSpecificationExecutor<User>{
	@Query("from User u where u.status != '离职'")
	public Page<User> findAll(Pageable pageable);
	
//	@Query("from User u inner join u.groupList g where g.groupName = ?1")
//	@Query("from User u,Group g where g.groupName = ?1")
//	public List<Object> findByGroup(String groupId);
	
	@Query(value = "select u.id_ from act_id_user u ,act_id_group g, act_id_membership ug where u.id_=ug.user_id_ and g.id_=ug.group_id_ and g.id_=?1",nativeQuery=true)
	public List<String> findByGroup(String groupId);
}
