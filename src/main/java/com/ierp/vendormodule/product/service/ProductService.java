package com.ierp.vendormodule.product.service;

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

import com.ierp.vendormodule.product.domain.Product;
import com.ierp.vendormodule.product.repository.ProductRepository;

@Service
@Transactional
public class ProductService implements IProductService{

	@Autowired
	private ProductRepository productRepository;
	@Override
	public Product save(Product entity) {
		return productRepository.save(entity);
	}

	@Override
	public Optional<Product> findById(Long id) {
		return productRepository.findById(id);
	}

	@Override
	public boolean existsById(Long id) {
		return productRepository.existsById(id);
	}

	@Override
	public long count() {
		return productRepository.count();
	}

	@Override
	public void deleteById(Long id) {
		productRepository.deleteById(id);
	}

	@Override
	public void deleteAll(Long[] ids) {
		List<Long> idLists = new ArrayList<Long>(Arrays.asList(ids));
		
		List<Product> products = (List<Product>) productRepository.findAllById(idLists);
		if(products!=null) {
			productRepository.deleteAll(products);
		}
	}
	@Override
	public Page<Product> findAll(Specification<Product> spec, Pageable pageable) {
		return productRepository.findAll(spec, pageable);
	}

}
