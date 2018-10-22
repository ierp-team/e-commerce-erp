package com.ierp.stockproduct.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ierp.stockproduct.domain.StockProduct;
import com.ierp.stockproduct.service.StockProductService;

public interface StockProductRepository extends PagingAndSortingRepository<StockProduct, Long>//分页和排序
,JpaSpecificationExecutor<StockProduct>{

}
