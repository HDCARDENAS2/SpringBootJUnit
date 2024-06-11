package com.learn.junit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.learn.junit.repository")
@EnableAspectJAutoProxy
public class SpringBootJUnit {
 
    public static void main(String[] args) {
        SpringApplication.run(SpringBootJUnit.class, args);
    }

}
