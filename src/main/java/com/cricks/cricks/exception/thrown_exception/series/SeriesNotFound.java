package com.cricks.cricks.exception.thrown_exception.series;

import com.cricks.cricks.exception.thrown_exception.MainException;

public class SeriesNotFound extends MainException{
  public SeriesNotFound(String message , Integer statusCode){
    super(message , statusCode);
  }
}