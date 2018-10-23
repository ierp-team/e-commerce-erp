package com.ierp.vendormodule.vendor.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.ierp.vendormodule.vendor.domain.Vendor;


public interface IVendorService {
	public Vendor save(Vendor entity);
	public Optional<Vendor> findById(Long id);
	public boolean existsById(Long id);
	public long count();
	public void deleteById(Long id);
	public void deleteAll(Long[] ids);
	public Vendor findByVendorName(String vendorName);
	public Page<Vendor> findAll(Specification<Vendor> spec, Pageable pageable);
}
