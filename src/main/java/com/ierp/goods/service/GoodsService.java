package com.ierp.goods.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.ierp.common.beans.BeanUtils;
import com.ierp.goods.domain.Goods;
import com.ierp.goods.domain.GoodsDTO;
import com.ierp.goods.repository.GoodsRepository;
import com.ierp.vendormodule.product.domain.Product;
import com.ierp.vendormodule.stockorder.domain.StockOrder;
import com.ierp.vendormodule.stockorder.service.IStockOrderService;
import com.ierp.vendormodule.stockproduct.domain.StockProduct;
import com.ierp.vendormodule.vendor.domain.Vendor;
import com.ierp.vendormodule.vendor.service.IVendorService;

@Service
public class GoodsService implements IGoodsService {

	@Autowired
	GoodsRepository goodsRepository;
	
	@Autowired
	IStockOrderService stockOrderService;
	
	@Autowired
	IVendorService vendorService;
	
	@Override
	public Goods save(GoodsDTO dto) {
		
		try {
			Goods entity = new Goods();
			GoodsDTO.dtoToEntity(dto, entity);
			
			Vendor vendor = vendorService.findById(dto.getVendorId()).get();
			if (null!=vendor) {
				entity.setVendor(vendor);
			}
			
			return goodsRepository.save(entity);
		} catch(Exception e) {
			return null;
		}
	}
	
	@Override
	public Goods update(GoodsDTO dto) {
		try {
			Goods entity= findById(dto.getId()).get();
			
			BeanUtils.copyProperties(dto, entity);//使用自定义的BeanUtils
			
			Vendor vendor = vendorService.findById(dto.getVendorId()).get();
			if (null!=vendor) {
				entity.setVendor(vendor);
			}
			
			return goodsRepository.save(entity);
		} catch(Exception e) {
			return null;
		}
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
	public Page<GoodsDTO> findAll(Specification<Goods> spec, Pageable pageable) {
		Page<Goods> entityPage = goodsRepository.findAll(spec, pageable);
		List<Goods> entityLists = entityPage.getContent();
		List<GoodsDTO> dtoLists = null;
		if(entityLists!=null) {
			dtoLists = new ArrayList<GoodsDTO>();
			for(Goods entity : entityLists) {
				GoodsDTO dto = new GoodsDTO();
				GoodsDTO.entityToDto(entity, dto);
				dtoLists.add(dto);
			}
		}
		return new PageImpl<GoodsDTO>(dtoLists, pageable, entityPage.getTotalElements());
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
	
	//商品入库，通过采购订单编码对采购的商品库存量进行增加
	public String goodsIntoStorage (String stockOrderNumber) {
		try {
			StockOrder stockOrder = stockOrderService.findByStockOrderNumber(stockOrderNumber);
			List<StockProduct> stockProductList = stockOrder.getStockProduct();
			for (StockProduct stockProduct : stockProductList) {
				Vendor vendor = stockProduct.getProduct().getVendor();
				Product product = stockProduct.getProduct();
				String goodsUuid = product.getProductUuid();
				Goods existGoods = goodsRepository.findByGoodsUuidAndVendor(goodsUuid, vendor);
				if (null == existGoods) {
					Goods goods = new Goods();
					goods.setGoodsCode(vendor.getVendorId()+goodsUuid);
					goods.setGoodsName(product.getProductName());
					goods.setGoodsPhoto(product.getProductPic());
					goods.setGoodsUuid(goodsUuid);
					goods.setGoodsDesc(product.getProductDesc());
					goods.setGoodsSpec(product.getProductSpec());
					goods.setVendor(vendor);
					goods.setSupplyPrice(product.getProductPrice());
					goods.setSalePrice(null);
					goods.setGoodsStock(stockProduct.getStockProductQuan());
					goodsRepository.save(goods);
				} else {
					existGoods.setGoodsStock(stockProduct.getStockProductQuan());
					goodsRepository.save(existGoods);
				}
			}
			return "入库成功！";
		} catch(Exception e) {
			return "入库失败！";
		}
	}

    @Override
    public List<Goods> findAll() {
        return (List<Goods>) goodsRepository.findAll();
    }

}
