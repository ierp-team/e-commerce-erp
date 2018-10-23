package com.ierp.vendormodule.vendor.service;

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
import com.ierp.vendormodule.vendor.domain.Vendor;
import com.ierp.vendormodule.vendor.repository.VendorRepository;

@Service
@Transactional
public class VendorService implements IVendorService{

	@Autowired
	private VendorRepository vendorRepository;
	@Override
	public Vendor save(Vendor entity) {
			return vendorRepository.save(entity);
	}

	@Override
	public Optional<Vendor> findById(Long id) {
		return vendorRepository.findById(id);
	}

	@Override
	public boolean existsById(Long id) {
		return vendorRepository.existsById(id);
	}

	@Override
	public long count() {
		return vendorRepository.count();
	}

	@Override
	public void deleteById(Long id) {
		vendorRepository.deleteById(id);
	}

	@Override
	public void deleteAll(Long[] ids) {
		List<Long> idLists = new ArrayList<Long>(Arrays.asList(ids));
		
		List<Vendor> vendors = (List<Vendor>) vendorRepository.findAllById(idLists);
		if(vendors!=null) {
			vendorRepository.deleteAll(vendors);
		}
	}

	@Override
	public Page<Vendor> findAll(Specification<Vendor> spec, Pageable pageable) {
		return vendorRepository.findAll(spec, pageable);
	}

	public Vendor findByVendorName(String vendorName) {
		return vendorRepository.findByVendorName(vendorName);
	}
}
