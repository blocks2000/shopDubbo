package com.jiang.productservice;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.SQLException;

@SpringBootTest
@RunWith(SpringRunner.class)
class ApplicationTests {

	@Autowired
	private DataSource dataSource;
	@Test
	void contextLoads() {
		try {
			System.out.println(dataSource.getConnection());
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
	}

}
