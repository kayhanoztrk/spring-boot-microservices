package com.photoapp.api.users.PhotoappApiUsers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication(scanBasePackages = "com.photoapp.api.users.PhotoappApiUsers.*")
@EnableDiscoveryClient
public class PhotoappApiUsersApplication {

	public static void main(String[] args) {
		SpringApplication.run(PhotoappApiUsersApplication.class, args);
	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder(){
		return new BCryptPasswordEncoder();
	}

}
