package com.ierp.vendormodule.product.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.ierp.vendormodule.product.domain.Product;

public interface IProductService {
	public Product save(Product entity);
	public Optional<Product> findById(Long id);
	public boolean existsById(Long id);
	public long count();
	public void deleteById(Long id);
	public void deleteAll(Long[] ids);
	public Page<Product> findAll(Specification<Product> spec, Pageable pageable);
}
