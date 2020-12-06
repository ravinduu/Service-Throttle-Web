package com.servicethrottle.servicethrottlebackend.configurations;

import com.servicethrottle.servicethrottlebackend.security.jwt.JWTFilter;
import com.servicethrottle.servicethrottlebackend.security.jwt.JWTProvider;
import com.servicethrottle.servicethrottlebackend.services.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

import static com.servicethrottle.servicethrottlebackend.models.enums.AuthorityType.ADMIN;
import static com.servicethrottle.servicethrottlebackend.models.enums.AuthorityType.CUSTOMER;

@EnableWebSecurity
public class SecurityConfigurations extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;
    private final JWTProvider jwtProvider;
    private final UserDetailsServiceImpl userDetailsServiceImpl;


    public SecurityConfigurations(@Qualifier("userDetailsServiceImpl") UserDetailsService userDetailsService, JWTProvider jwtProvider, UserDetailsServiceImpl userDetailsServiceImpl) {
        this.userDetailsService = userDetailsService;
        this.jwtProvider = jwtProvider;
        this.userDetailsServiceImpl = userDetailsServiceImpl;
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers("/v2/api-docs",
                "/swagger-resources/**",
                "/swagger-ui.html");
    }

    @Autowired
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .cors().and()
                .addFilter(new UsernamePasswordAuthenticationFilter())
                .addFilterAfter(new JWTFilter(jwtProvider,userDetailsServiceImpl), UsernamePasswordAuthenticationFilter.class)
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/st/login").permitAll()
                .antMatchers("/st/register").permitAll()
                .antMatchers("/st/activate").permitAll()
                .antMatchers("/st/hello").permitAll()
                .anyRequest().authenticated();
    }

    @Bean
    protected PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

}
