package com.willington.test.fundinvestment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class FundinvestmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(FundinvestmentApplication.class, args);
	}

}
