package com.academy.security.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class MyAppConfiguration extends WebSecurityConfigurerAdapter {
  private final UserDetailsService userDetailsService;

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    //    auth.inMemoryAuthentication()
//        .withUser("user").password("{noop}password").roles("USER")
//        .and()
//        .withUser("admin").password("{noop}password").roles("ADMIN");
    auth.authenticationProvider(authenticationProvider());
  }

  @Bean
  public DaoAuthenticationProvider authenticationProvider() {
    var authenticationProvider = new DaoAuthenticationProvider();
    authenticationProvider.setUserDetailsService(userDetailsService);
    authenticationProvider.setPasswordEncoder(passwordEncoder());

    return authenticationProvider;
  }

  @Bean
  public BCryptPasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
        .httpBasic()
        .and()
        .authorizeRequests()
        .antMatchers(HttpMethod.GET, "/books").hasAnyRole("USER");
  }
}
