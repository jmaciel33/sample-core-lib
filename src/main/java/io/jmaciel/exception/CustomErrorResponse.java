package io.jmaciel.exception;

import java.util.List;
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
public class CustomErrorResponse {

  private String errorCode;
  private String reason;
  private List<CustomErrorMessage> errors;

}
