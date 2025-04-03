package io.jmaciel.exception;

import lombok.Getter;

@Getter
public class ArchitectureException extends BaseRuntimeException {

  public ArchitectureException(CustomExceptionResponse customExceptionResponse) {
    super(customExceptionResponse);
  }

  public ArchitectureException(CustomExceptionResponse customExceptionResponse, Exception e) {
    super(customExceptionResponse, e);
  }

  public ArchitectureException(CustomExceptionResponse customExceptionResponse, String message) {
    super(customExceptionResponse, message);
  }

  public ArchitectureException(CustomExceptionResponse customExceptionResponse, String message,
      Exception e) {
    super(customExceptionResponse, message, e);
  }
}
