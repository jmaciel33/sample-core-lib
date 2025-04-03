package io.jmaciel.log;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CustomLogMessage {

  private String _logLevel;
  private String className;
  private String message;
  private String traceId;
  private String correlationId;
}
