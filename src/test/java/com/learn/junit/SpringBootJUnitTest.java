package com.learn.junit;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(value = "/application.properties")
public class SpringBootJUnitTest {

	@Test
	void contextLoads() {
		assertTrue(true, "This is a basic example test that always passes.");
	}

}
