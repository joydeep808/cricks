package com.cricks.cricks.exception.thrown_exception.match;

import com.cricks.cricks.exception.thrown_exception.MainException;

public class MatchException extends MainException{
  
  public MatchException(String message , Integer statusCode){
    super(message , statusCode);
  }
  
}
