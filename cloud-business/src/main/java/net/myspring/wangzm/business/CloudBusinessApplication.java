package net.myspring.wangzm.business;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
public class CloudBusinessApplication {

	public static void main(String[] args) {
		SpringApplication.run(CloudBusinessApplication.class, args);
	}
}
