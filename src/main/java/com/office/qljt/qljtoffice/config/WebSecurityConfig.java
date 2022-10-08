package com.office.qljt.qljtoffice.config;

import com.office.qljt.qljtoffice.handler.AuthenticationFailHandlerImpl;
import com.office.qljt.qljtoffice.handler.AuthenticationProviderImpl;
import com.office.qljt.qljtoffice.handler.AuthenticationSuccessHandlerImpl;
import com.office.qljt.qljtoffice.handler.LogoutSuccessHandlerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.session.HttpSessionEventPublisher;


/**
 * Security配置类
 *
 * @author lib
 * @date 2021/07/29
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private AuthenticationSuccessHandlerImpl authenticationSuccessHandler;
    @Autowired
    private AuthenticationFailHandlerImpl authenticationFailHandler;
    @Autowired
    private LogoutSuccessHandlerImpl logoutSuccessHandler;

    @Bean
    public SessionRegistry sessionRegistry() {
        return new SessionRegistryImpl();
    }

    @Bean
    public HttpSessionEventPublisher httpSessionEventPublisher() {
        return new HttpSessionEventPublisher();
    }
    
    /**
     * 密码加密
     *
     * @return {@link PasswordEncoder} 加密方式
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Autowired
    private AuthenticationProviderImpl authenticationProvider;

    /**
     * 自定义密码验证
     *
     * @param auth auth
     * @throws Exception Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider);
    }

    /**
     * 配置权限
     *
     * @param http http
     * @throws Exception 异常
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 配置登录注销路径
        http.formLogin()
                .loginProcessingUrl("/login")
                .successHandler(authenticationSuccessHandler)
                .failureHandler(authenticationFailHandler)
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessHandler(logoutSuccessHandler);
        // 配置路由权限信息
        http.authorizeRequests()
                .anyRequest().permitAll()
                .and()
                // 关闭跨站请求防护
                .csrf().disable().exceptionHandling()
                .and()
                .sessionManagement()
                .maximumSessions(20)
                .sessionRegistry(sessionRegistry());
    }

}
