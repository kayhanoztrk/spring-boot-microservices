package com.photoapp.api.users.PhotoappApiUsers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication(scanBasePackages = "com.photoapp.api.users.PhotoappApiUsers.*")
@EnableDiscoveryClient
public class PhotoappApiUsersApplication {

	public static void main(String[] args) {
		SpringApplication.run(PhotoappApiUsersApplication.class, args);
	}

}
