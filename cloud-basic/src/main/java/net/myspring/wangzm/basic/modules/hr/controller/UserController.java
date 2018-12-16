package net.myspring.wangzm.basic.modules.hr.controller;

import net.myspring.wangzm.basic.common.rabbit.UserSource;
import net.myspring.wangzm.basic.common.utils.SecurityUtils;
import net.myspring.wangzm.basic.modules.hr.client.UserClient;
import net.myspring.wangzm.basic.modules.hr.domain.Account;
import net.myspring.wangzm.util.json.ObjectMapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;

@EnableBinding({UserSource.class})
@Controller
public class UserController {

    @Autowired
    UserSource goodsOrderSource;
    @Autowired
    private UserClient userClient;

    @RequestMapping("/user")
    @ResponseBody
    public Principal user(Principal user) {
        SecurityUtils.getAccountId();
        for(int i=0;i<10;i++){
            goodsOrderSource.userOutput().send(MessageBuilder.withPayload(i).build());
        }
        return user;
    }

    @StreamListener(UserSource.USER_INPUT)
    public void inputSource(Message<String> message) {
        System.out.println(message.getPayload() + "：Return");
    }

    @StreamListener(UserSource.DLX_INPUT)
    public void dlxSource(Message<String> message) {
        System.out.println(message.getPayload() + "：DLX");
    }
}
