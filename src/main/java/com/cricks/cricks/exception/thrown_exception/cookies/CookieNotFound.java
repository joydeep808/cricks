package com.cricks.cricks.exception.thrown_exception.cookies;

import com.cricks.cricks.exception.thrown_exception.MainException;

public class CookieNotFound extends MainException{    
  public CookieNotFound(String message , Integer statusCode){
    super(message , statusCode);
  }
  
}
