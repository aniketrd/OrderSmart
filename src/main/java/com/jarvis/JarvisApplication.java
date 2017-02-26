package com.jarvis;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("classpath:applicationContext.xml")
public class JarvisApplication {

	public static void main(String[] args) {
		SpringApplication.run(JarvisApplication.class, args);
		System.out.println("Server started Successfully");
	}
}
