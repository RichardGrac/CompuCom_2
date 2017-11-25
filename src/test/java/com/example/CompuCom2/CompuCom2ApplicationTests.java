package com.example.CompuCom2;

import com.example.CompuCom2.repository.Estadisticas;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CompuCom2ApplicationTests {

	@Autowired
	Estadisticas estadisticas;
	Log LOG = LogFactory.getLog(CompuCom2ApplicationTests.class);

	@Test
	public void contextLoads() {
		LOG.info(estadisticas.monthWithMorePurchases().toString());
	}

}
