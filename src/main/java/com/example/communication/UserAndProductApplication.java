package com.example.communication;

import com.example.communication.controller.UserController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@SpringBootApplication
public class UserAndProductApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(UserAndProductApplication.class, args);
//		ApplicationContext ctx = SpringApplication.run(UserAndProductApplication.class, args);
//		UserController userController =ctx.getBean(UserController.class);
//		userController.getToken();
	}


	@Bean
	public RestTemplate restTemplate(){
		return new RestTemplate();
	}

//	@Bean
//	public UserController userController()
//	{
//		return new UserController();
//	}
}
