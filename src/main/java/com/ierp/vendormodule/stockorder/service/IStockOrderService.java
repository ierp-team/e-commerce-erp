package com.ierp.vendormodule.stockorder.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.ierp.vendormodule.stockorder.domain.StockOrder;

public interface IStockOrderService {
	public StockOrder save(StockOrder entity);
	public Optional<StockOrder> findById(Long id);
	public boolean existsById(Long id);
	public long count();
	public void deleteById(Long id);
	public void deleteAll(Long[] ids);
	public StockOrder findByStockOrderNumber(String stockOrderNumber);
	public Page<StockOrder> findAll(Specification<StockOrder> spec, Pageable pageable);
}
