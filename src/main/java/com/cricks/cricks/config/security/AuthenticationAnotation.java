package com.cricks.cricks.config.security;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface AuthenticationAnotation {
  Role role() default Role.ADMIN;
  public enum Role{
    ADMIN,
    USER
  }
}
