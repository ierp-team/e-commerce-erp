package com.ierp.logistics.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import com.ierp.logistics.domain.Logistics;


/**
 * PagingAndSortingRepository：分页和排序
 * JpaSpecificationExecutor：自定义高级（动态条件组装）查询
 *
 */
@Repository
public interface LogisticsRepository extends PagingAndSortingRepository<Logistics, Long>
										, JpaSpecificationExecutor<Logistics> {

	
}
