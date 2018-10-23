package com.ierp;

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
		for (int i=0; i<10; i++) {
			Expressco expressco = new Expressco();
			expressco.setExpresscoCode("00"+i);
			expressco.setExpresscoName("快递"+i);
			expresscoService.save(expressco);
		}
	}
}
