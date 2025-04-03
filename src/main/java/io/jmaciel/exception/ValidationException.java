package io.jmaciel.exception;

import lombok.Getter;

@Getter
public class ValidationException extends BaseCheckedException {


  public ValidationException(CustomExceptionResponse customExceptionResponse) {
    super(customExceptionResponse);
  }

  public ValidationException(CustomExceptionResponse customExceptionResponse, Exception e) {
    super(customExceptionResponse, e);
  }

  public ValidationException(CustomExceptionResponse customExceptionResponse, String message) {
    super(customExceptionResponse, message);
  }

  public ValidationException(CustomExceptionResponse customExceptionResponse, String message,
      Exception e) {
    super(customExceptionResponse, message, e);
  }
}
