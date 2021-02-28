package com.example.springsecurity.config;

import com.example.springsecurity.filter.ApiKeyAuthenticationFilter;
import com.example.springsecurity.provider.ApiKeyAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;

import java.util.Collections;

/*
   - Configuration: Adds a new Spring configuration
   - Enable web security: Overrides the default web security configuration
*/
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired private ApiKeyAuthenticationProvider apiKeyAuthenticationProvider;

  @Override
  protected void configure(HttpSecurity httpSecurity) throws Exception {
    httpSecurity
        .authorizeRequests()
        .anyRequest()
        .authenticated()
        .and()
        .addFilterBefore(
            new ApiKeyAuthenticationFilter(authenticationManager()),
            AnonymousAuthenticationFilter.class);
  }

  @Bean
  public AuthenticationManager authenticationManager() {
    return new ProviderManager(Collections.singletonList(apiKeyAuthenticationProvider));
  }
}
