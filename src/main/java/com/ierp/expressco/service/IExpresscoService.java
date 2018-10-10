package com.ierp.expressco.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.ierp.expressco.domain.Expressco;

public interface IExpresscoService {
	
	public Expressco save(Expressco entity);
	
	public void deleteById(Long id);
	public void deleteAll(Long[] ids);
	
	public Optional<Expressco> findById(Long id);
	public Page<Expressco> findAll(Specification<Expressco> spec, Pageable pageable);
	public boolean existsById(Long id);
	public long count();
	
}
