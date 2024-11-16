package com.cricks.cricks.exception.thrown_exception.cookies;

import com.cricks.cricks.exception.thrown_exception.MainException;

public class UnauthorizedAccess extends MainException{  
  public UnauthorizedAccess(String message , Integer statusCode){
    super(message , statusCode);
  }
}
