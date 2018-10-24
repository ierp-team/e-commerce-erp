package com.ierp.vendormodule.vendor.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ierp.permissionmodule.user.domain.User;
import com.ierp.vendormodule.vendor.domain.Vendor;



public interface VendorRepository extends PagingAndSortingRepository<Vendor, Long>//分页和排序
,JpaSpecificationExecutor<Vendor>{
	@Query
	Vendor findByVendorName(String vendorName);
	
	@Query
	Vendor findByUser(User user);
}
