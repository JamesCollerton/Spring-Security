package com.example.springsecurity.config;

import com.example.springsecurity.filter.ApiKeyAuthenticationFilter;
import com.example.springsecurity.provider.ApiKeyAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;

import java.util.Collections;

/*
   TODO: What are all of these annotations
*/
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableCaching
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
