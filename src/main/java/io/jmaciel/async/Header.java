package io.jmaciel.async;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Header {

  private String timestamp;
  private String routingKey;
  private String source;
  private String traceId;
  private String correlationId;

}
