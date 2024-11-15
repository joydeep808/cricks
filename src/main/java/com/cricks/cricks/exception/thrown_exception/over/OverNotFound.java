package com.cricks.cricks.exception.thrown_exception.over;

import com.cricks.cricks.exception.thrown_exception.MainException;

public class OverNotFound extends MainException{
  public OverNotFound(String message , Integer statusCode){
    super(message, statusCode);

  }
  
}
