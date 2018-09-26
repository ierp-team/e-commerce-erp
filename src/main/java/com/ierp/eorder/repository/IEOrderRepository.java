package com.ierp.eorder.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.ierp.eorder.domain.EOrder;

/**
 * @author ghb, Date:Sep 26, 20182:54:17 PM
 *
 */
@Repository
public interface IEOrderRepository extends PagingAndSortingRepository<EOrder, Long>,JpaSpecificationExecutor<EOrder>{

}
