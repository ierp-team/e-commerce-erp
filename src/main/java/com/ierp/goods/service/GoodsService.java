package com.ierp.goods.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.ierp.goods.domain.Goods;
import com.ierp.goods.repository.GoodsRepository;

@Service
public class GoodsService implements IGoodsService {

	@Autowired
	GoodsRepository goodsRepository;
	
	@Override
	public Goods save(Goods entity) {
		return goodsRepository.save(entity);
	}

	@Override
	public void deleteById(Long goodsId) {
		goodsRepository.deleteById(goodsId);

	}

	@Override
	public void deleteAll(Long[] goodsIds) {
		goodsRepository.deleteAll();
	}
	
	@Override
	public Optional<Goods> findById(Long goodsId) {
		return goodsRepository.findById(goodsId);
	}

	@Override
	public Page<Goods> findAll(Specification<Goods> spec, Pageable pageable) {
		return goodsRepository.findAll(spec, pageable);
	}

	@Override
	public boolean existsById(Long goodsId) {
		return goodsRepository.existsById(goodsId);
	}

	public long count() {
		return goodsRepository.count();
	}

}
