package com.example.springsecurity.config;

import com.example.springsecurity.filter.ApiKeyAuthenticationFilter;
import com.example.springsecurity.service.ClientAuthenticationSecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationProvider;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableCaching
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired private ApiKeyAuthenticationFilter apiKeyAuthenticationFilter;

  @Autowired private ClientAuthenticationSecurityService clientAuthSecurityService;

  @Override
  protected void configure(HttpSecurity httpSecurity) throws Exception {
    httpSecurity
        .authorizeRequests()
        .anyRequest()
        .permitAll()
        .and()
        .addFilterBefore(apiKeyAuthenticationFilter, AnonymousAuthenticationFilter.class);
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) {
    auth.authenticationProvider(authenticatedAuthenticationProvider());
  }

  @Bean
  public PreAuthenticatedAuthenticationProvider authenticatedAuthenticationProvider() {
    return new PreAuthenticatedAuthenticationProvider() {
      {
        setPreAuthenticatedUserDetailsService(clientAuthSecurityService);
      }
    };
  }
}
