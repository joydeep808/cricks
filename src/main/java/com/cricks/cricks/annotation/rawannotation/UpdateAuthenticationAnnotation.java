package com.cricks.cricks.annotation.rawannotation;

import java.lang.annotation.*;


@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface UpdateAuthenticationAnnotation {
  String teamId() default "";
}
