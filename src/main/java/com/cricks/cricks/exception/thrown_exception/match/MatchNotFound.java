package com.cricks.cricks.exception.thrown_exception.match;

import com.cricks.cricks.exception.thrown_exception.MainException;

import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper=false)
public class MatchNotFound  extends MainException{
  public MatchNotFound(String message , Integer statusCode){
    super(message , statusCode);
  }
}
