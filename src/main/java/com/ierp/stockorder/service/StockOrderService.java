package com.ierp.stockorder.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.ierp.stockorder.domain.StockOrder;
import com.ierp.stockorder.repository.StockOrderRepository;

@Service
@Transactional
public class StockOrderService implements IStockOrderService{

	@Autowired
	private StockOrderRepository stockOrderRepository;
	@Override
	public StockOrder save(StockOrder entity) {
		return stockOrderRepository.save(entity);
	}

	@Override
	public Optional<StockOrder> findById(Long id) {
		return stockOrderRepository.findById(id);
	}

	@Override
	public boolean existsById(Long id) {
		return stockOrderRepository.existsById(id);
	}

	@Override
	public long count() {
		return stockOrderRepository.count();
	}

	@Override
	public void deleteById(Long id) {
		stockOrderRepository.deleteById(id);
	}

	@Override
	public void deleteAll(Long[] ids) {
		List<Long> idLists = new ArrayList<Long>(Arrays.asList(ids));
		
		List<StockOrder> stockOrders = (List<StockOrder>) stockOrderRepository.findAllById(idLists);
		if(stockOrders!=null) {
			stockOrderRepository.deleteAll(stockOrders);
		}
	}

	@Override
	public Page<StockOrder> findAll(Specification<StockOrder> spec, Pageable pageable) {
		return stockOrderRepository.findAll(spec, pageable);
	}
	
}
