package com.ierp.product.service;

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

import com.ierp.product.domain.Product;
import com.ierp.product.domain.ProductDisplayDTO;
import com.ierp.product.repository.ProductRepository;
import com.ierp.vendor.domain.Vendor;
import com.ierp.vendor.repository.VendorRepository;

@Service
@Transactional
public class ProductService implements IProductService{

	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private VendorRepository vendorRepository;
	
	@Override
	public Product save(ProductDisplayDTO dto) {
		Product entity;
		if(null!=dto.getProductId()) {
			entity = productRepository.findById(dto.getProductId()).get();
		} else {
			entity = new Product();
		}
		ProductDisplayDTO.dtoToEntity(dto, entity);
		//关联关系
		if(dto.getVendorId()!=null) {
			Optional<Vendor> vendor = vendorRepository.findById(dto.getVendorId());
			entity.setVendor(vendor.get());
		}
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
	public Page<ProductDisplayDTO> findAll(Specification<Product> spec, Pageable pageable) {
		Page<Product> entityPage = productRepository.findAll(spec, pageable);
		List<Product> entityLists = entityPage.getContent();
		List<ProductDisplayDTO> dtoLists = null;
		if(entityLists!=null) {
			dtoLists = new ArrayList<ProductDisplayDTO>();
			for(Product entity : entityLists) {
				ProductDisplayDTO dto = new ProductDisplayDTO();
				ProductDisplayDTO.entityToDto(entity, dto);
				dtoLists.add(dto);
			}
		}
		return new PageImpl<ProductDisplayDTO>(dtoLists, pageable, entityPage.getTotalElements());	
	}

//	@Override
//	public Vendor findByVendorAccount(String vendorAccount) {
//		return productRepository.findByVendorAccount(vendorAccount);
//	}
}
