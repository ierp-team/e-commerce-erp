package com.ierp.vendormodule.stockproduct.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.ierp.vendormodule.stockproduct.domain.StockProduct;
import com.ierp.vendormodule.stockproduct.domain.StockProductDisplayDTO;

public interface IStockProductService {
	public StockProduct save(StockProduct entity);
	public Optional<StockProduct> findById(Long id);
	public boolean existsById(Long id);
	public long count();
	public void deleteById(Long id);
	public void deleteAll(Long[] ids);
	public Page<StockProductDisplayDTO> findAll(Specification<StockProduct>spec,Pageable pageable);
}
