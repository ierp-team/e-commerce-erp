package com.ierp.vendormodule.vendor.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.ierp.permissionmodule.user.domain.User;
import com.ierp.vendormodule.vendor.domain.Vendor;
import com.ierp.vendormodule.vendor.domain.VendorDTO;


public interface IVendorService {
	public Vendor save(Vendor entity);
	public Optional<Vendor> findById(Long id);
	public boolean existsById(Long id);
	public long count();
	public void deleteById(Long id);
	public void deleteAll(Long[] ids);
	public Vendor findByVendorName(String vendorName);
	public List<Vendor> getList();
	public Vendor findByUser(User user);
	public Page<VendorDTO> findAll(Specification<Vendor> spec, Pageable pageable);
}
