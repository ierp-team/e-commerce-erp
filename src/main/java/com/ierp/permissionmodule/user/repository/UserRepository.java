package com.ierp.permissionmodule.user.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.ierp.permissionmodule.user.domain.User;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, String>,JpaSpecificationExecutor<User>{
    
}
