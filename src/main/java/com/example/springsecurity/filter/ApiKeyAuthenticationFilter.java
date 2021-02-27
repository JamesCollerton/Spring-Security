package com.example.springsecurity.filter;

import com.example.springsecurity.model.ApiKeyAuthenticationToken;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@Slf4j
@Component
public class ApiKeyAuthenticationFilter extends OncePerRequestFilter {

  @Override
  protected void doFilterInternal(
      HttpServletRequest request, HttpServletResponse response, FilterChain chain)
      throws ServletException, IOException {

    Optional<String> apiKeyOptional = Optional.ofNullable(request.getHeader("x-api-key"));

    Authentication tokenAuth =
        apiKeyOptional
            .map(apiKey -> new PreAuthenticatedAuthenticationToken(apiKey, apiKey))
            .orElse(new PreAuthenticatedAuthenticationToken("anonymous", ""));

    ApiKeyAuthenticationToken token = new ApiKeyAuthenticationToken(apiKey);

    SecurityContextHolder.getContext().setAuthentication(tokenAuth);

    chain.doFilter(request, response);
  }
}
