package com.ierp;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.ierp.goods.domain.Goods;
import com.ierp.goods.domain.GoodsDTO;
import com.ierp.goods.service.IGoodsService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GoodsServiceTests {

	
	@Autowired
	IGoodsService goodsService;
	
	@Test
	public void testSave() {
		for(int i=0; i<100; i++) {
			GoodsDTO goods = new GoodsDTO();
			goods.setGoodsCode("0000"+i);
			goods.setGoodsName("牛奶"+i);
			goods.setGoodsPhoto("1.png");
			goods.setGoodsUuid("1234"+i);
			goods.setGoodsDesc("蓝瓶的好喝的");
			goods.setGoodsSpec("350ml");
			//goods.setVendorId(1000l);
			goods.setSupplyPrice(5f);
			goods.setSalePrice(10f);
			goods.setGoodsStock(50);
			goodsService.save(goods);
		}	
	}
	
	@Test
	public void testSumGoodsStock() {
		int sum = goodsService.sumGoodsStock("123466");
		System.out.println(sum);
	}
	
	@Test
	public void testFindByGoodsUuid() {
		List<Goods> uuidGoodsList = goodsService.findByGoodsUuid("123466");
		System.out.println(uuidGoodsList);
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void testGoodsPicking() {
		System.out.println(goodsService.goodsPicking("123466", 51));
	}
	
}
