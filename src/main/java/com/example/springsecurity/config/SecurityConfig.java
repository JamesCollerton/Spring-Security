package com.example.springsecurity.config;

import com.example.springsecurity.filter.ApiKeyAuthenticationFilter;
import com.example.springsecurity.provider.ApiKeyAuthenticationProvider;
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

import java.util.Arrays;

/*
   TODO: What are all of these annotations
*/
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableCaching
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  //  @Autowired private ApiKeyAuthenticationFilter apiKeyAuthenticationFilter;

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

  //  @Override
  //  protected void configure(AuthenticationManagerBuilder auth) {
  //    auth.authenticationProvider(apiKeyAuthenticationProvider());
  //  }

  @Bean
  public AuthenticationManager authenticationManager() {
    return new ProviderManager(Arrays.asList(new ApiKeyAuthenticationProvider()));
  }

  //  @Bean
  //  public ApiKeyAuthenticationProvider apiKeyAuthenticationProvider() {
  //    return new ApiKeyAuthenticationProvider();
  //  }
}
