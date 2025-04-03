package io.jmaciel.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomExceptionResponse {

  private String correlationId;
  private String traceId;
  private CustomErrorResponse customErrorResponse;

  public CustomExceptionResponse(CustomErrorResponse customErrorResponse) {
    this.customErrorResponse = customErrorResponse;
  }

}
