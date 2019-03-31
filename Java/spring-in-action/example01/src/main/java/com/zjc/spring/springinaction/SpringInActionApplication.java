package com.zjc.spring.springinaction;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SpringInActionApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringInActionApplication.class, args);
	}

	@RequestMapping(path="/hello")
	public String hello() {
		return "Hello Spring!";
	}
}
