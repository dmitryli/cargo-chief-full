package com.cargochief;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations = "classpath:test-application.properties")
public class CargoChiefTests {

	@Autowired
	JdbcTemplate template;

	@Autowired
	DataScriptUtils utils;

	@Test
	public void rebuildDatabase() {
		utils.rebuildDemoDatabase();
	}

}
