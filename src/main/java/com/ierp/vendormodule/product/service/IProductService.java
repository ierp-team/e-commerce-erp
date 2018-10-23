package com.ierp.vendormodule.product.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.ierp.vendormodule.product.domain.Product;
import com.ierp.vendormodule.product.domain.ProductDisplayDTO;
import com.ierp.vendormodule.vendor.domain.Vendor;

public interface IProductService {
	public Product save(ProductDisplayDTO dto);
	public Optional<Product> findById(Long id);
	public boolean existsById(Long id);
	public long count();
	public void deleteById(Long id);
	public void deleteAll(Long[] ids);
	public Page<ProductDisplayDTO> findAll(Specification<Product> spec, Pageable pageable);
//	public Vendor findByVendorAccount(String vendorAccount);
}
