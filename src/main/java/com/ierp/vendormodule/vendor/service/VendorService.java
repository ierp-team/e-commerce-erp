package com.ierp.vendormodule.vendor.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.ierp.permissionmodule.user.domain.User;
import com.ierp.vendormodule.vendor.domain.Vendor;
import com.ierp.vendormodule.vendor.domain.VendorDTO;
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
	public Page<VendorDTO> findAll(Specification<Vendor> spec, Pageable pageable) {
		Page<Vendor> entityPage = vendorRepository.findAll(spec, pageable);
		List<Vendor> entityLists = entityPage.getContent();
		List<VendorDTO> dtoLists = null;
		if(entityLists!=null) {
			dtoLists = new ArrayList<VendorDTO>();
			for(Vendor entity : entityLists) {
				VendorDTO dto = new VendorDTO();
				VendorDTO.entityToDto(entity, dto);
				dtoLists.add(dto);
			}
		}
		return new PageImpl<VendorDTO>(dtoLists, pageable, entityPage.getTotalElements());	
	}

	public Vendor findByVendorName(String vendorName) {
		return vendorRepository.findByVendorName(vendorName);
	}
	
	@Override
	public List<Vendor> getList() {
		Iterator<Vendor> iter = vendorRepository.findAll().iterator();
		List<Vendor> list = new ArrayList<Vendor>();
		while (iter.hasNext()) {
			list.add(iter.next());
		}
		return list;
	}

	@Override
	public Vendor findByUser(User user) {
		return vendorRepository.findByUser(user);
	}
}
