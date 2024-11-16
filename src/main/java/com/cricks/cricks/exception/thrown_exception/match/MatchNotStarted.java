package com.cricks.cricks.exception.thrown_exception.match;

import com.cricks.cricks.exception.thrown_exception.MainException;

public class MatchNotStarted extends MainException  {
  public MatchNotStarted(String message , Integer statusCode){
      super(message, statusCode);
  }
  
}
