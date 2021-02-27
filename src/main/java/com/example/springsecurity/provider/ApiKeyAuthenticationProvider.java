package com.example.springsecurity.provider;

import com.example.springsecurity.model.ApiKeyAuthenticationToken;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.util.ObjectUtils;

public class ApiKeyAuthenticationProvider implements AuthenticationProvider {

  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    String apiKey = (String) authentication.getPrincipal();

    if (ObjectUtils.isEmpty(apiKey)) {
      throw new InsufficientAuthenticationException("No API key in request");
    } else {
      if ("ValidApiKey".equals(apiKey)) {
        return new ApiKeyAuthenticationToken(apiKey, true);
      }
      throw new BadCredentialsException("API Key is invalid");
    }
  }

  @Override
  public boolean supports(Class<?> authentication) {
    return ApiKeyAuthenticationToken.class.isAssignableFrom(authentication);
  }
}
