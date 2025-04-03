package io.jmaciel.exception.handler;

import io.jmaciel.exception.UnauthorizedException;
import io.jmaciel.traceability.ObservabilityHeaders;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.jboss.logging.MDC;

@Provider
public class UnauthorizedExceptionHandler implements ExceptionMapper<UnauthorizedException> {

  @Override
  public Response toResponse(UnauthorizedException e) {
    var response = e.getCustomExceptionResponse();
    response.setCorrelationId(MDC.get(ObservabilityHeaders.X_CORRELATION_ID.getHeaderName()).toString());
    response.setTraceId(MDC.get(ObservabilityHeaders.X_TRACE_ID.getHeaderName()).toString());
    return Response.status(Status.UNAUTHORIZED.getStatusCode()).entity(response).build();
  }
}
