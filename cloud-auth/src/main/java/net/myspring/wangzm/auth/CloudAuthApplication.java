package net.myspring.wangzm.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@SpringBootApplication
@EnableRedisHttpSession
@EnableFeignClients
public class CloudAuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(CloudAuthApplication.class, args);
	}
}
