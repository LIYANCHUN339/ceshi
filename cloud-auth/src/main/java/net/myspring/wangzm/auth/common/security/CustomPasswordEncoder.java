package net.myspring.wangzm.auth.common.security;

import net.myspring.wangzm.util.text.StringUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Created by wangzm on 2017 2017/11/19.
 */
public class CustomPasswordEncoder extends BCryptPasswordEncoder {

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return StringUtils.validatePassword(rawPassword.toString(),encodedPassword);
    }
}
