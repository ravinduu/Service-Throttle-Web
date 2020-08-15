package com.servicethrottle.stuaaservice.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .authorizeRequests()
                .antMatchers("/account/register").permitAll()
                .antMatchers("/account/activate/**").permitAll()
                .antMatchers("/account/resend-code").permitAll()
                .antMatchers("/customer/**").permitAll()
                .antMatchers("/customer/delete/**").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers("/account/finish/**").permitAll()
                .antMatchers("/account/reset-password/init/**").permitAll()
                .antMatchers("/account/reset-password/finish").permitAll()
                .anyRequest().authenticated();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        super.configure(auth);
    }

    @Bean
    protected PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
