package com.example.CompuCom2;

import com.example.CompuCom2.utils.storage.StorageProperties;
import com.example.CompuCom2.utils.storage.StorageService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

//@EntityScan{
//	basePackageClasses = {CompuCom2Application.class, Jsr301JpaConverters.class}
//}

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class CompuCom2Application {

	public static void main(String[] args) {
		SpringApplication.run(CompuCom2Application.class, args);
	}

	@Bean
	CommandLineRunner init(StorageService storageService) {
		return (args) -> {
			storageService.init();
		};
	}
}
