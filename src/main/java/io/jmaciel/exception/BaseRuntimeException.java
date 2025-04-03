package io.jmaciel.exception;

import lombok.Getter;

@Getter
public abstract class BaseRuntimeException extends RuntimeException {

  private final CustomExceptionResponse customExceptionResponse;
  private final Exception innerException;

  public BaseRuntimeException(CustomExceptionResponse customExceptionResponse) {
    super(customExceptionResponse.getCustomErrorResponse().getReason());
    this.customExceptionResponse = customExceptionResponse;
    this.innerException = null;
  }

  public BaseRuntimeException(CustomExceptionResponse customExceptionResponse, Exception e) {
    super(customExceptionResponse.getCustomErrorResponse().getReason() + "," + e.getMessage(), e);
    this.customExceptionResponse = customExceptionResponse;
    this.innerException = e;
  }

  public BaseRuntimeException(CustomExceptionResponse customExceptionResponse, String message) {
    super(customExceptionResponse.getCustomErrorResponse().getReason() + "," + message);
    this.customExceptionResponse = customExceptionResponse;
    this.innerException = null;
  }

  public BaseRuntimeException(CustomExceptionResponse customExceptionResponse, String message, Exception e) {
    super(customExceptionResponse.getCustomErrorResponse().getReason() + "," + message, e);
    this.customExceptionResponse = customExceptionResponse;
    this.innerException = e;
  }
}
