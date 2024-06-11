package com.isi.auth_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AuthServiceApplication {

	public static void main(String[] args) {


		SpringApplication.run(AuthServiceApplication.class, args);

		/*SpringApplication application = new SpringApplication(AuthServiceApplication.class);
		application.setWebApplicationType(WebApplicationType.NONE);
		application.run(args);*/
	}

}
