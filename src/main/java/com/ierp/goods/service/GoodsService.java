package com.ierp.goods.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
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
	public void deleteById(Long id) {
		goodsRepository.deleteById(id);

	}

	@Override
	public void deleteAll(Long[] ids) {
		List<Long> idLists = new ArrayList<Long>(Arrays.asList(ids));
		
		List<Goods> goodsList = (List<Goods>) goodsRepository.findAllById(idLists);
		if(goodsList!=null) {
			goodsRepository.deleteAll(goodsList);
		}
	}
	
	@Override
	public Optional<Goods> findById(Long id) {
		return goodsRepository.findById(id);
	}

	@Override
	public Page<Goods> findAll(Specification<Goods> spec, Pageable pageable) {
		return goodsRepository.findAll(spec, pageable);
	}

	@Override
	public boolean existsById(Long id) {
		return goodsRepository.existsById(id);
	}

	@Override
	public long count() {
		return goodsRepository.count();
	}
	
	//计算同一uuid商品的库存总量
	@Override
	public int sumGoodsStock(String uuid) {
		return goodsRepository.sumGoodsStock(uuid);
	}
	
	//查询同一uuid的全部商品
	@Override
	public List<Goods> findByGoodsUuid(String uuid) {
		return goodsRepository.findByGoodsUuid(uuid);
	}
	
	//根据uuid和需要的数量进行拣货，更新库存
	@Override
	public String goodsPicking(String uuid, int needReduceSumNum) {
		
		if (goodsRepository.sumGoodsStock(uuid) >= needReduceSumNum) {
			
			//LinkedHashMap<Long, Integer> goodsIdNumMap = new LinkedHashMap<>();
			List<Goods> uuidGoodsList = goodsRepository.findByGoodsUuid(uuid);
			for (Goods goods : uuidGoodsList) {
	            if (needReduceSumNum <= 0) {
	                break;
	            }
	            int ocpNum = Math.min(goods.getGoodsStock(), needReduceSumNum); 
	            needReduceSumNum = needReduceSumNum - ocpNum;
	            //goodsIdNumMap.put(goods.getId(), ocpNum);
	            goods.setGoodsStock(goods.getGoodsStock()-ocpNum);			//更新商品库存量
	            goodsRepository.save(goods);			//保存更新
	        }
	        //System.out.println(goodsIdNumMap);
	        return "拣货成功！";
		} else {
			return "拣货失败！库存量不够，需进货";
		}		
	}

    @Override
    public List<Goods> findAll() {
        return (List<Goods>) goodsRepository.findAll();
    }

}
