package com.fullstack3.bff_emergencias;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class BffEmergenciasApplication {

	public static void main(String[] args) {
		SpringApplication.run(BffEmergenciasApplication.class, args);
	}

}
