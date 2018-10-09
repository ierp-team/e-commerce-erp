package com.ierp.eordermodule.eorderproduct.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ierp.eordermodule.eorderproduct.domain.EOrderProduct;

public interface IEOrderProductRepository  extends PagingAndSortingRepository<EOrderProduct, Long>,JpaSpecificationExecutor<EOrderProduct>{

    @Query("from EOrderProduct eOrderProduct where eOrderProduct.orderProductStatus = ?1") 
    public Page<EOrderProduct> findEOrder(String orderStatus,Pageable pageable); 
    
    @Query("from EOrderProduct eOrderProduct  where eOrderProduct.eOrder.id = ?1")
    public List<EOrderProduct> findEOrderProducts(Long id);
}
