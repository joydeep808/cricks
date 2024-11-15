package com.cricks.cricks.exception.thrown_exception.match;

import com.cricks.cricks.exception.thrown_exception.MainException;

public class MatchAlreadyExist extends MainException{
    public MatchAlreadyExist(String message , Integer statusCode){
      super(message , statusCode);
    }
}
