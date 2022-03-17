package com.da_java.p8_trippricer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class P8TripPricerApplication {

	public static void main(String[] args) {
		SpringApplication.run(P8TripPricerApplication.class, args);
	}

}
