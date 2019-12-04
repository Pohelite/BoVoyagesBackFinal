package fr.bovoyages;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import fr.bovoyages.images.StorageProperties;
import fr.bovoyages.images.StorageService;

@SpringBootApplication
public class BoVoyagesFinalBackApplication
{

	public static void main(String[] args) {
		SpringApplication.run(BoVoyagesFinalBackApplication.class, args);
	}
	
	@Bean
	CommandLineRunner init(StorageService storageService) {
			return (args) -> {
				storageService.deleteAll();
				storageService.init();
			};}
}
