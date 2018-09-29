package com.ierp.expressco.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import com.ierp.expressco.domain.Expressco;


/**
 * PagingAndSortingRepository：分页和排序
 * PagingAndSortingRepository：自定义高级（动态条件组装）查询
 *
 */
@Repository
public interface ExpresscoRepository extends PagingAndSortingRepository<Expressco, Long>
										, JpaSpecificationExecutor<Expressco> {

}
