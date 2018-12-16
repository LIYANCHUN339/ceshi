package net.myspring.wangzm.auth.common.security;

import com.google.common.collect.Sets;
import net.myspring.wangzm.auth.modules.sys.domain.Account;
import net.myspring.wangzm.auth.modules.sys.repository.AccountRepository;
import net.myspring.wangzm.util.text.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Set;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        CustomUserDetails customUserDetails = null;
        if(StringUtils.isBlank(username)){
            return null;
        }
        Account account=accountRepository.findByLoginName(username);
        if(account==null){
            return null;
        }
        Set<SimpleGrantedAuthority> authList = Sets.newHashSet();
        customUserDetails = new CustomUserDetails(
                username,
                account.getPassword(),
                true,
                true,
                true,
                true,
                authList,
                account.getId(),
                "1",
                "1",
                "1"
        );
        return customUserDetails;
    }
}
