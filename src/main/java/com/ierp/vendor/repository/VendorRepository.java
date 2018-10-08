package com.ierp.vendor.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ierp.vendor.domain.Vendor;



public interface VendorRepository extends PagingAndSortingRepository<Vendor, Long>//分页和排序
,JpaSpecificationExecutor<Vendor>{

}
