package net.myspring.wangzm.auth.common.security;

import net.myspring.wangzm.common.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.web.authentication.switchuser.SwitchUserFilter;

import javax.servlet.http.HttpServletRequest;

public class CustomSwitchUserFilter extends SwitchUserFilter {

    @Autowired
    private TokenStore tokenStore;

    @Override
    protected Authentication attemptSwitchUser(HttpServletRequest request) throws AuthenticationException {
        Authentication authentication=super.attemptSwitchUser(request);
        try {
            OAuth2Authentication oAuth2Authentication= (OAuth2Authentication) SecurityContextHolder.getContext().getAuthentication();
            OAuth2AccessToken accessToken=tokenStore.readAccessToken(((OAuth2AuthenticationDetails)oAuth2Authentication.getDetails()).getTokenValue());
            tokenStore.storeAccessToken(accessToken,new OAuth2Authentication(oAuth2Authentication.getOAuth2Request(),authentication));
        }catch (Exception e){
            throw new ServiceException("用户切换失败");
        }
        return authentication;
    }
}
