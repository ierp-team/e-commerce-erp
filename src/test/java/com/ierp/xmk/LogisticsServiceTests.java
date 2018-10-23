package com.ierp.xmk;

import static org.assertj.core.api.Assertions.extractProperty;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ierp.expressco.domain.Expressco;
import com.ierp.expressco.service.IExpresscoService;
import com.ierp.logistics.domain.Logistics;
import com.ierp.logistics.domain.LogisticsRequestDTO;
import com.ierp.logistics.service.ILogisticsService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LogisticsServiceTests {

	@Autowired
	ILogisticsService logisticsService;
	
	@Autowired
	IExpresscoService expresscoService;
	
	@Test
	public void testSave() {
		LogisticsRequestDTO dto = new LogisticsRequestDTO();
		dto.setEorderCode("001");
		dto.setExpresscoCode("ZTO");
		dto.setPayType(1);
		dto.setExpType("标准快件");
		dto.setIsReturnSignBill(1);
		dto.setIsReturnPrintTemplate("1");
		dto.setSenderProvinceName("广东省");
		dto.setSenderCityName("东莞市");
		dto.setSenderExpAreaName("松山湖");
		dto.setSenderAddress("大学路1号");
		dto.setSenderContact("XX电子商务公司");
		dto.setSenderPhone("0755-1111111");
		dto.setProvinceName("广东省");
		dto.setCityName("深圳市");
		dto.setExpAreaName("福田区");
		dto.setAddress("深南大道2009号");
		dto.setContact("张三");
		dto.setPhone("13709076789");
		dto.setGoodsType("食品");
		dto.setWeight(3.2d);
		logisticsService.save(dto);
	}
	
	@Test
	public void testDelete() {
		logisticsService.deleteById(1l);;
		
	}
	
	@Test
	public void testFind() {
		Logistics logistics = logisticsService.findById(1l).get();
		System.out.println(logistics.getLogisticsCompany().getExpresscoName());
		
	}
	
}
