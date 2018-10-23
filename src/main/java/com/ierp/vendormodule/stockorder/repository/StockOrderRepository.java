package com.ierp.vendormodule.stockorder.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ierp.vendormodule.stockorder.domain.StockOrder;

public interface StockOrderRepository extends PagingAndSortingRepository<StockOrder, Long>//分页和排序
,JpaSpecificationExecutor<StockOrder>{


}
