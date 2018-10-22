package com.ierp.stockorder.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ierp.stockorder.domain.StockOrder;

public interface StockOrderRepository extends PagingAndSortingRepository<StockOrder, Long>//分页和排序
,JpaSpecificationExecutor<StockOrder>{

}
