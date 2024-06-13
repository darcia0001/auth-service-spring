package com.isi.auth_service;

import configuration.AuthenticationFilter;
import configuration.GatewayConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.isi.auth_service", "configuration"})
public class AuthServiceApplication {
	@Autowired
	private GatewayConfig conf ;
	private AuthenticationFilter filter ;




    public static void main(String[] args) {

		SpringApplication.run(AuthServiceApplication.class, args);


	}

}
