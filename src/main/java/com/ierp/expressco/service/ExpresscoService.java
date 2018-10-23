package com.ierp.expressco.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
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
	public void deleteById(Long id) {
		expresscoRepository.deleteById(id);

	}

	@Override
	public void deleteAll(Long[] ids) {
		List<Long> idLists = new ArrayList<Long>(Arrays.asList(ids));
		
		List<Expressco> expresscoList = (List<Expressco>) expresscoRepository.findAllById(idLists);
		if(expresscoList!=null) {
			expresscoRepository.deleteAll(expresscoList);
		}
	}
	
	@Override
	public Optional<Expressco> findById(Long id) {
		return expresscoRepository.findById(id);
	}

	@Override
	public Page<Expressco> findAll(Specification<Expressco> spec, Pageable pageable) {
		return expresscoRepository.findAll(spec, pageable);
	}
	
	@Override
	public List<Expressco> getList() {
		Iterator<Expressco> iter = expresscoRepository.findAll().iterator();
		List<Expressco> list = new ArrayList<Expressco>();
		while (iter.hasNext()) {
			list.add(iter.next());
		}
		return list;
	}

	@Override
	public boolean existsById(Long id) {
		return expresscoRepository.existsById(id);
	}

	@Override
	public long count() {
		return expresscoRepository.count();
	}
	
	@Override
	public Expressco findExpressco(String expresscoName) {
		return expresscoRepository.findExpressco(expresscoName);
	}
	
	@Override
	public Expressco findByExpresscoCode(String expresscoCode) {
		return expresscoRepository.findByExpresscoCode(expresscoCode);
	}
	
}
