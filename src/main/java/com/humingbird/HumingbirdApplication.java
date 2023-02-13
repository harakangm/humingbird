package com.humingbird;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication
public class HumingbirdApplication {

	public static void main(String[] args) {
		SpringApplication.run(HumingbirdApplication.class, args);
	}

}
