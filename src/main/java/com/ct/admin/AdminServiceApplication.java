package com.ct.admin;



import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;





@SpringBootApplication
public class AdminServiceApplication {

	private Logger logger = LogManager.getLogger(AdminServiceApplication.class);
	public static void main(String[] args) {
		
		SpringApplication.run(AdminServiceApplication.class, args);
	
	}
	
	@Bean
	@LoadBalanced
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
	
	@Bean
	public Logger log() {
		return logger;
	}
	
}
