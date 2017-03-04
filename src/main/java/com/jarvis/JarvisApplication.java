package com.jarvis;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("classpath:applicationContext.xml")
public class JarvisApplication {

	private static final Logger logger = LoggerFactory.getLogger(JarvisApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(JarvisApplication.class, args);
		logger.info("Server started Successfully");

	}
}
