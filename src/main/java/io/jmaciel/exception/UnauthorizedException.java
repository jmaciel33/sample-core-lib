package io.jmaciel.exception;

import lombok.Getter;

@Getter
public class UnauthorizedException extends BaseCheckedException {

  public UnauthorizedException(CustomExceptionResponse customExceptionResponse) {
    super(customExceptionResponse);
  }

  public UnauthorizedException(CustomExceptionResponse customExceptionResponse, Exception e) {
    super(customExceptionResponse, e);
  }

  public UnauthorizedException(CustomExceptionResponse customExceptionResponse, String message) {
    super(customExceptionResponse, message);
  }

  public UnauthorizedException(CustomExceptionResponse customExceptionResponse, String message,
      Exception e) {
    super(customExceptionResponse, message, e);
  }
}
