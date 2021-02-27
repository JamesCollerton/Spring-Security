package com.example.springsecurity.model;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.Transient;
import org.springframework.security.core.authority.AuthorityUtils;

@Transient
public class ApiKeyAuthenticationToken extends AbstractAuthenticationToken {

  private String apiKey;

  /*
   TODO: What is NO AUTHORITIES
  */
  public ApiKeyAuthenticationToken(String apiKey, boolean authenticated) {
    super(AuthorityUtils.NO_AUTHORITIES);
    this.apiKey = apiKey;
    setAuthenticated(authenticated);
  }

  public ApiKeyAuthenticationToken(String apiKey) {
    super(AuthorityUtils.NO_AUTHORITIES);
    this.apiKey = apiKey;
    setAuthenticated(false);
  }

  public ApiKeyAuthenticationToken() {
    super(AuthorityUtils.NO_AUTHORITIES);
    setAuthenticated(false);
  }

  @Override
  public Object getCredentials() {
    return null;
  }

  /*
   TODO: What is principal
  */
  @Override
  public Object getPrincipal() {
    return apiKey;
  }
}
