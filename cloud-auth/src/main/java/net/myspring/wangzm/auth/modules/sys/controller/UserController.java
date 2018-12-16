package net.myspring.wangzm.auth.modules.sys.controller;

import net.myspring.wangzm.auth.modules.sys.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;

@Controller
public class UserController {

    @Autowired
    private UserService userService;


    @RequestMapping("/user")
    @ResponseBody
    public Principal user(Principal user) {
        return user;
    }

    @RequestMapping("/user/logout")
    @ResponseBody
    public Boolean logout(Principal user) {
        if (user instanceof OAuth2Authentication) {
            OAuth2Authentication oAuth2Authentication = (OAuth2Authentication) user;
            Object detail = oAuth2Authentication.getDetails();
            if (detail instanceof OAuth2AuthenticationDetails) {
                String tokenValue = ((OAuth2AuthenticationDetails) oAuth2Authentication.getDetails()).getTokenValue();
                return userService.deleteAccessToken(tokenValue);
            }
        }
        return false;
    }
}
