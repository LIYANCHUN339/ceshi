package net.myspring.wangzm.basic.modules.hr.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;

@FeignClient("cloud-duty")
public interface UserClient {

    @RequestMapping(value = "user",method = RequestMethod.GET)
    Principal findUser();
}
