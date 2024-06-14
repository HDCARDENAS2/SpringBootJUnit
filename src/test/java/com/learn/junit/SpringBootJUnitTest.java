package com.learn.junit;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(value = "/application.properties")
public class SpringBootJUnitTest {

	@Test
	void contextLoads() {

	}

}
