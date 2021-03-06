package com.ierp.eordermodule.eorder.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.ierp.eordermodule.eorder.domain.EOrder;
import com.ierp.eordermodule.eorderproduct.domain.EOrderProduct;


/**
 * @author ghb, Date:Sep 26, 20182:54:17 PM
 *
 */
@Repository
public interface IEOrderRepository extends PagingAndSortingRepository<EOrder, Long>,JpaSpecificationExecutor<EOrder>{

    @Query("from EOrder eOrder where eOrder.orderStatus = ?1") 
    public List<EOrder> findEOrder(String orderStatus); 
    
    
}
