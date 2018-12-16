package net.myspring.wangzm.auth.common.config;

import net.myspring.wangzm.auth.common.security.CustomPasswordEncoder;
import net.myspring.wangzm.auth.common.security.CustomSwitchUserFilter;
import net.myspring.wangzm.auth.common.security.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.switchuser.SwitchUserFilter;

@Configuration
@Order(3)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/**/**.html");
        web.ignoring().antMatchers("/**/**.css");
        web.ignoring().antMatchers("/**/**.js");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsService);
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .formLogin()
                .loginPage("/login")
                .and()
                //下面的配置使HttpSecurity接收以配置的请求请求，其他请求不提供springsecurity的拦截器。
                .requestMatchers()
                .antMatchers("/login","/oauth/token", "/oauth/authorize", "/oauth/confirm_access")
                .and()
                .authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .httpBasic()
                .and().addFilterAt(switchUserFilter(), FilterSecurityInterceptor.class)
                .csrf().disable();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new CustomPasswordEncoder();
    }

    @Bean
    public SwitchUserFilter switchUserFilter(){
        CustomSwitchUserFilter switchUserFilter = new CustomSwitchUserFilter();
        switchUserFilter.setUserDetailsService(customUserDetailsService);
        switchUserFilter.setSwitchUserUrl("/switchUser");
        switchUserFilter.setTargetUrl("/user");
        return switchUserFilter;
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(customUserDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }
}