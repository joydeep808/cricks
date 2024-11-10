package com.cricks.cricks.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

import lombok.RequiredArgsConstructor;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurtyFilter {
  private AuthenticationProvider authenticationProvider;

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws  Exception{

   return  httpSecurity
    .cors((cors)-> cors.disable())
    .csrf(csrf-> csrf.disable())
    .sessionManagement(sess-> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
    .authenticationProvider(authenticationProvider)
    .authorizeHttpRequests(request->{
      request.anyRequest().permitAll();
    }).build();

  }

}
