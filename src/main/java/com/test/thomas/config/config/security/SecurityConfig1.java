package com.test.thomas.config.config.security;

import com.test.thomas.config.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.security.web.authentication.ForwardAuthenticationFailureHandler;
import org.springframework.security.web.authentication.ForwardAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by thomas on 2018/2/28.
 */
@EnableWebSecurity
public class SecurityConfig1 extends WebSecurityConfigurerAdapter {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new StandardPasswordEncoder("MD5");
    }

    @Autowired
    private CustomerService customerService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/login","/css/**").permitAll()
                .antMatchers("/js/**").permitAll()
                .antMatchers("/user/**").authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .and()
                .logout()
                .logoutSuccessUrl("/login");
        http.addFilterBefore(myValidCodeProcessingFilter(authenticationManager()),UsernamePasswordAuthenticationFilter.class);
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("thomas")
                .password(new StandardPasswordEncoder("MD5").encode("12345"))
                .roles("MANAGER");
    }

    @Bean(name = "frontAuthenticationProvider")
    public MyAuthenticationProvider frontAuthenticationProvider() {
        MyAuthenticationProvider myAuthenticationProvider = new MyAuthenticationProvider();
        myAuthenticationProvider.setUserDetailsService(customerService);
        myAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return myAuthenticationProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager() {
        List<AuthenticationProvider> list = new ArrayList<>();
        list.add(frontAuthenticationProvider());
        AuthenticationManager authenticationManager = new ProviderManager(list);
        return authenticationManager;
    }

    @Bean
    public MyValidCodeProcessingFilter myValidCodeProcessingFilter(AuthenticationManager authenticationManager) {
        System.out.println("init---------------------------->MyValidCodeProcessingFilter");
        MyValidCodeProcessingFilter filter = new MyValidCodeProcessingFilter();
        filter.setAuthenticationManager(authenticationManager);
        filter.setAuthenticationSuccessHandler(frontAuthenticationSuccessHandler());
        filter.setAuthenticationFailureHandler(frontAuthenticationFailureHandler());
        return filter;
    }

    @Bean
    public FrontAuthenticationFailureHandler frontAuthenticationFailureHandler() {
        return new FrontAuthenticationFailureHandler("/login");
    }

    @Bean
    public FrontAuthenticationSuccessHandler frontAuthenticationSuccessHandler() {
        return new FrontAuthenticationSuccessHandler("/user/index");
    }

    @Bean
    public MyAuthenticationEntryPoint myAuthenticationEntryPoint() {
        return new MyAuthenticationEntryPoint("/login");
    }

}
