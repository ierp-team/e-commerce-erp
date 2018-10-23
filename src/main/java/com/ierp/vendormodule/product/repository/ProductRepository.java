package com.ierp.vendormodule.product.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Component;

import com.ierp.vendormodule.product.domain.Product;
import com.ierp.vendormodule.vendor.domain.Vendor;



@Component
public interface ProductRepository extends PagingAndSortingRepository<Product, Long>//分页和排序
,JpaSpecificationExecutor<Product>{
//	@Query
//	Vendor findByVendorAccount(String vendorAccount);
}
