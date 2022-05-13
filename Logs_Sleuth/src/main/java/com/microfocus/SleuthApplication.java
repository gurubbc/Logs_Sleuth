package com.microfocus;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@RestController
public class SleuthApplication {

	@Autowired
	RestTemplate restTemplate;
	
	private static final Logger LOG=Logger.getLogger(SleuthApplication.class.getName());
	public static void main(String[] args) {
		SpringApplication.run(SleuthApplication.class, args);
		System.out.println("Sleuth app started");

	}
	
	@Bean
	public RestTemplate giveMeRestTemplateObject()
	{
		return new RestTemplate();
	}
	
	
	@RequestMapping("/guru") // first access
	public String index()
	{
		LOG.log(Level.INFO, "executing index method");
		return restTemplate.getForObject("http://localhost:8888/", String.class);
		
	}

	@RequestMapping("/")
	public String home() // request goes to here , 2nd ms (assumption)
	{
		LOG.log(Level.INFO, "Calling Home");
		return "Hello World";
	}
	
}
