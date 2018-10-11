package com.ierp.permissionmodule.group.repository;


import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
//import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.ierp.permissionmodule.group.domain.Group;

//@Component
@Repository
public interface GroupRepository extends PagingAndSortingRepository<Group, String>,JpaSpecificationExecutor<Group>{
	
}
