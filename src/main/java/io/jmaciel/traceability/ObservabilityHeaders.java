package io.jmaciel.traceability;

public enum ObservabilityHeaders {
  X_TRACE_ID("x-trace-id"),
  X_CORRELATION_ID("x-correalation-id");

  private final String headerName;

  ObservabilityHeaders(String headerName) {
    this.headerName = headerName;
  }

  public String getHeaderName() {
    return headerName;
  }
}
