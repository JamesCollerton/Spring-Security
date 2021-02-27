package com.example.springsecurity.service;

import com.example.springsecurity.model.OrderUserDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

@Slf4j
@Component
public class ClientAuthenticationSecurityService
    implements AuthenticationUserDetailsService<PreAuthenticatedAuthenticationToken> {

  @Override
  public UserDetails loadUserDetails(PreAuthenticatedAuthenticationToken token) {

    String apiKey = (String) token.getCredentials();

    if (ObjectUtils.isEmpty(apiKey)) {
      throw new InsufficientAuthenticationException("No API key in request");
    } else {
      if ("ValidApiKey".equals(apiKey)) {
        return new OrderUserDetails();
      }

      throw new BadCredentialsException("API Key is invalid");
    }
  }
}
