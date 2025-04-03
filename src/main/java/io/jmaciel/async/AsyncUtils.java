package io.jmaciel.async;

import io.jmaciel.traceability.ObservabilityHeaders;
import org.jboss.logging.MDC;

public class AsyncUtils {

  private AsyncUtils() {
    throw new IllegalStateException("Utility class");
  }

  private static Header createHeader(final String routingKey, final String source) {
    return Header.builder()
        .timestamp(String.valueOf(System.currentTimeMillis()))
        .routingKey(routingKey)
        .source(source)
        .traceId((String) MDC.get(ObservabilityHeaders.X_TRACE_ID.getHeaderName()))
        .correlationId((String) MDC.get(ObservabilityHeaders.X_CORRELATION_ID.getHeaderName()))
        .build();
  }

  public static AsyncMessage createAsyncMessage(final String routingKey, final String source, final Object payload) {
    return AsyncMessage.builder()
        .header(createHeader(routingKey, source))
        .payload(payload)
        .build();
  }

}
