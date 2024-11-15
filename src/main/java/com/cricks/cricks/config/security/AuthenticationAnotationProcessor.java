package com.cricks.cricks.config.security;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class AuthenticationAnotationProcessor {
  
  @Before(value = "annotation(com.cricks.cricks.config.security.AuthenticationAnotation")
  public void checkAuthentication(ProceedingJoinPoint joinPoint) throws Exception {


  }
}
