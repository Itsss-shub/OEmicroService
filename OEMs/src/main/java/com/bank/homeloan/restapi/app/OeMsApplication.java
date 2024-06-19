package com.bank.homeloan.restapi.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class OeMsApplication {

	public static void main(String[] args) {
		SpringApplication.run(OeMsApplication.class, args);
	}

}
