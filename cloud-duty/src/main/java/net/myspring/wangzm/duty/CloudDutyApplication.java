package net.myspring.wangzm.duty;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import net.myspring.wangzm.duty.common.rabbit.DutyUserSources;
import net.myspring.wangzm.duty.modules.domain.Account;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.SendTo;

import java.io.IOException;

@SpringBootApplication
@EnableEurekaClient
@EnableBinding({DutyUserSources.class})
public class CloudDutyApplication {

	public static void main(String[] args) {
		SpringApplication.run(CloudDutyApplication.class, args);
	}

	@StreamListener(DutyUserSources.DUTY_USER_INPUT)
	@SendTo(DutyUserSources.DUTY_USER_OUTPUT)
	public String inputSource(Message<String> message, @Header(AmqpHeaders.CHANNEL) Channel channel,
							  @Header(AmqpHeaders.DELIVERY_TAG) long tag) throws IOException {
		System.out.println(message.getPayload()+" from basic");
		if(Integer.valueOf(message.getPayload())%3==0){
			channel.basicReject(tag,false);
		}
		return message.getPayload();
	}

}
