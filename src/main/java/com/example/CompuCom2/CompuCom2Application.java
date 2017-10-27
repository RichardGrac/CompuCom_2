package com.example.CompuCom2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CompuCom2Application {

	public static void main(String[] args) {
		SpringApplication.run(CompuCom2Application.class, args);
	}

//	@Bean
//	CommandLineRunner init(StorageService storageService) {
//		return (args) -> {
////			storageService.deleteAll();
//			storageService.init();
//		};
//	}
}
