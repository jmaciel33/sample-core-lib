package io.jmaciel.exception;

import lombok.Getter;

@Getter
public class NotFoundException extends BaseCheckedException{

  public NotFoundException(CustomExceptionResponse customExceptionResponse) {
    super(customExceptionResponse);
  }

  public NotFoundException(CustomExceptionResponse customExceptionResponse, Exception e) {
    super(customExceptionResponse, e);
  }

  public NotFoundException(CustomExceptionResponse customExceptionResponse, String message) {
    super(customExceptionResponse, message);
  }

  public NotFoundException(CustomExceptionResponse customExceptionResponse, String message,
      Exception e) {
    super(customExceptionResponse, message, e);
  }
}
