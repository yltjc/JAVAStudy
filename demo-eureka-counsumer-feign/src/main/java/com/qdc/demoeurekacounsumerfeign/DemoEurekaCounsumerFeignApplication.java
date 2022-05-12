package com.qdc.demoeurekacounsumerfeign;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableEurekaClient
@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients
public class DemoEurekaCounsumerFeignApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoEurekaCounsumerFeignApplication.class, args);
	}

}
