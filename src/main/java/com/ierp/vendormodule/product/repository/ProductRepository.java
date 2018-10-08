package com.ierp.vendormodule.product.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Component;

import com.ierp.vendormodule.product.domain.Product;


@Component
public interface ProductRepository extends PagingAndSortingRepository<Product, Long>//分页和排序
,JpaSpecificationExecutor<Product>{

}
