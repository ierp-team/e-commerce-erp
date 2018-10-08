package com.ierp.goods.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.ierp.goods.domain.Goods;

public interface IGoodsService {
	
	public Goods save(Goods entity);
	
	public void deleteById(Long goodsId);
	public void deleteAll(Long[] goodsIds);
	
	public Optional<Goods> findById(Long goodsId);
	public Page<Goods> findAll(Specification<Goods> spec, Pageable pageable);
	public boolean existsById(Long goodsId);
	public long count();
	
}
