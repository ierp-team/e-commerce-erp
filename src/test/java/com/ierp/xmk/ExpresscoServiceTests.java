package com.ierp.xmk;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ierp.expressco.domain.Expressco;
import com.ierp.expressco.service.IExpresscoService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ExpresscoServiceTests {

	@Autowired
	IExpresscoService expresscoService;
	
	@Test
	public void testSave() {
		Expressco expressco1 = new Expressco();
		Expressco expressco2 = new Expressco();
		Expressco expressco3 = new Expressco();
		
		expressco1.setExpresscoCode("ZTO");
		expressco1.setExpresscoName("中通");
		expresscoService.save(expressco1);
		
		expressco2.setExpresscoCode("YTO");
		expressco2.setExpresscoName("圆通");
		expresscoService.save(expressco2);
		
		expressco3.setExpresscoCode("SF");
		expressco3.setExpresscoName("顺丰");
		expresscoService.save(expressco3);
	}
}
