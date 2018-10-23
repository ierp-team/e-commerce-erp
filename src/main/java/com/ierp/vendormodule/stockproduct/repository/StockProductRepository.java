package com.ierp.vendormodule.stockproduct.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ierp.vendormodule.stockproduct.domain.StockProduct;
import com.ierp.vendormodule.stockproduct.service.StockProductService;

public interface StockProductRepository extends PagingAndSortingRepository<StockProduct, Long>//分页和排序
,JpaSpecificationExecutor<StockProduct>{

}
