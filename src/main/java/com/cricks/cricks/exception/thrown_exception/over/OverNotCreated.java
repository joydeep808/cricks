package com.cricks.cricks.exception.thrown_exception.over;

import com.cricks.cricks.exception.thrown_exception.MainException;

public class OverNotCreated extends MainException{  

  public OverNotCreated(String message , Integer statusCode){
    super(message , statusCode);
  }
  
}
