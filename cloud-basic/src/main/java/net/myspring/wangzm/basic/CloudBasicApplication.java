package net.myspring.wangzm.basic;

import net.myspring.wangzm.basic.common.rabbit.UserSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.integration.support.MessageBuilder;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class CloudBasicApplication {

	public static void main(String[] args) {
		SpringApplication.run(CloudBasicApplication.class, args);
	}

}
