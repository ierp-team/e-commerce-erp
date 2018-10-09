package com.ierp.goods.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.ierp.goods.domain.Goods;

public interface IGoodsService {
	
	public Goods save(Goods entity);
	
	public void deleteById(Long id);
	public void deleteAll(Long[] ids);
	
	public Optional<Goods> findById(Long id);
	public Page<Goods> findAll(Specification<Goods> spec, Pageable pageable);
	public boolean existsById(Long id);
	public long count();
	
	/* 自定义接口 */
	public int sumGoodsStock(String uuid);			//计算同一uuid商品的库存总量
	public List<Goods> findByGoodsUuid(String uuid);			//查询同一uuid的全部商品
	public String goodsPicking(String uuid, int needReduceSumNum);		//拣货，更新库存
	
}
