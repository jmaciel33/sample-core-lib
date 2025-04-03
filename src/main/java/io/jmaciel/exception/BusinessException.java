package io.jmaciel.exception;

import lombok.Getter;

@Getter
public class BusinessException extends BaseCheckedException {

  public BusinessException(CustomExceptionResponse customExceptionResponse) {
    super(customExceptionResponse);
  }

  public BusinessException(CustomExceptionResponse customExceptionResponse, Exception e) {
    super(customExceptionResponse, e);
  }

  public BusinessException(CustomExceptionResponse customExceptionResponse, String message) {
    super(customExceptionResponse, message);
  }

  public BusinessException(CustomExceptionResponse customExceptionResponse, String message,
      Exception e) {
    super(customExceptionResponse, message, e);
  }
}
