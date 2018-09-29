package com.ierp.expressco.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.ierp.expressco.domain.Expressco;
import com.ierp.expressco.repository.ExpresscoRepository;

@Service
public class ExpresscoService implements IExpresscoService {

	@Autowired
	ExpresscoRepository expresscoRepository;
	
	@Override
	public Expressco save(Expressco entity) {
		return expresscoRepository.save(entity);
	}

	@Override
	public void deleteById(Long expresscoId) {
		expresscoRepository.deleteById(expresscoId);

	}

	@Override
	public void deleteAll(Long[] expresscoIds) {
		expresscoRepository.deleteAll();
	}
	
	@Override
	public Optional<Expressco> findById(Long expresscoId) {
		return expresscoRepository.findById(expresscoId);
	}

	@Override
	public Page<Expressco> findAll(Specification<Expressco> spec, Pageable pageable) {
		return expresscoRepository.findAll(spec, pageable);
	}

	@Override
	public boolean existsById(Long expresscoId) {
		return expresscoRepository.existsById(expresscoId);
	}

	public long count() {
		return expresscoRepository.count();
	}

}
