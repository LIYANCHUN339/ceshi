package net.myspring.wangzm.auth.modules.sys.service;

import net.myspring.wangzm.auth.modules.sys.domain.Account;
import net.myspring.wangzm.auth.modules.sys.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.token.JdbcClientTokenServices;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private ConsumerTokenServices consumerTokenServices;

    public Boolean deleteAccessToken(String accessToken){
        return consumerTokenServices.revokeToken(accessToken);
    }
}
