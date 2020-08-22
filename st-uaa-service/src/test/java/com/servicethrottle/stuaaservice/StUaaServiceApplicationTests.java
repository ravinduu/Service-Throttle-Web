package com.servicethrottle.stuaaservice;

import com.servicethrottle.stuaaservice.controllers.AccountController;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class StUaaServiceApplicationTests {

	@Autowired
	private AccountController accountController;


	@Test
	void contextLoads() throws Exception{
		Assertions.assertThat(accountController).isNotNull();
	}

}
