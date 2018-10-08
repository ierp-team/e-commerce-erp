package com.ierp.permissionmodule.group.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Component;

import com.ierp.permissionmodule.group.domain.Group;

@Component
public interface GroupRepository extends PagingAndSortingRepository<Group, String>,JpaSpecificationExecutor<Group>{
    
}
