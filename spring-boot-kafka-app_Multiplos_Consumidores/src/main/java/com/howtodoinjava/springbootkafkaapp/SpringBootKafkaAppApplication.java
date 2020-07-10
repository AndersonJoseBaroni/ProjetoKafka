package com.howtodoinjava.springbootkafkaapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@ComponentScan(basePackages = {"com.howtodoinjava.springbootkafkaapp.service"})
//@ComponentScan ({"com.howtodoinjava.springbootkafkaapp", "com.howtodoinjava.springbootkafkaapp.service", "com.howtodoinjava.springbootkafkaapp.controller"})
public class SpringBootKafkaAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootKafkaAppApplication.class, args);
	}

}
