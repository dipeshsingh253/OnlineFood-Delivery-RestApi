package com.foodexpress;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class FoodExpress1Application {
//localhost:8088/swagger-ui/
	public static void main(String[] args) {
		SpringApplication.run(FoodExpress1Application.class, args);
	}

}
