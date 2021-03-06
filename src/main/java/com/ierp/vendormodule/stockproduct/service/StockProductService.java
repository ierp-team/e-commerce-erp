package com.ierp.vendormodule.stockproduct.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.ierp.vendormodule.product.domain.ProductDisplayDTO;
import com.ierp.vendormodule.stockproduct.domain.StockProduct;
import com.ierp.vendormodule.stockproduct.domain.StockProductDisplayDTO;
import com.ierp.vendormodule.stockproduct.repository.StockProductRepository;

@Service
@Transactional
public class StockProductService implements IStockProductService{

	@Autowired
	private StockProductRepository stockProductRepository;

	@Override
	public StockProduct save(StockProduct entity) {
		return stockProductRepository.save(entity);
	}

	@Override
	public Optional<StockProduct> findById(Long id) {
		return stockProductRepository.findById(id);
	}

	@Override
	public boolean existsById(Long id) {
		return stockProductRepository.existsById(id);
	}

	@Override
	public long count() {
		return stockProductRepository.count();
	}

	@Override
	public void deleteById(Long id) {
		stockProductRepository.findById(id);
	}

	@Override
	public void deleteAll(Long[] ids) {
		List<Long> idLists = new ArrayList<Long>(Arrays.asList(ids));
		
		List<StockProduct> stockProducts = (List<StockProduct>) stockProductRepository.findAllById(idLists);
		if(stockProducts!=null) {
			stockProductRepository.deleteAll(stockProducts);
		}
	}

	@Override
	public Page<StockProductDisplayDTO> findAll(Specification<StockProduct>spec,Pageable pageable) {
		Page<StockProduct> entityPage = stockProductRepository.findAll(spec, pageable);
		List<StockProduct> entityLists = entityPage.getContent();
		List<StockProductDisplayDTO> dtoLists = null;
		if(entityLists!=null) {
			dtoLists = new ArrayList<StockProductDisplayDTO>();
			for(StockProduct entity : entityLists) {
				StockProductDisplayDTO dto = new StockProductDisplayDTO();
				StockProductDisplayDTO.entityToDto(entity, dto);
				dtoLists.add(dto);
			}
		}
		return new PageImpl<StockProductDisplayDTO>(dtoLists, pageable, entityPage.getTotalElements());
	}
	
}
