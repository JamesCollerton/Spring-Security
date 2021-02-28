package com.example.springsecurity.model;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.Transient;
import org.springframework.security.core.authority.AuthorityUtils;

/*
   A marker for Authentications that should never be stored across requests, for example a bearer token authentication
*/
@Transient
public class ApiKeyAuthenticationToken extends AbstractAuthenticationToken {

  private String apiKey;

  /*
   GrantedAuthority as being a "permission" or a "right". Those "permissions" are (normally) expressed as
   strings (with the getAuthority() method). Those strings let you identify the permissions and let your
   voters decide if they grant access to something.
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

   The identity of the principal being authenticated. In the case of an authentication
   request with username and password, this would be the username.
  */
  @Override
  public Object getPrincipal() {
    return apiKey;
  }
}
