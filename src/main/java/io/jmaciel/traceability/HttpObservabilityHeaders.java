package io.jmaciel.traceability;

import io.jmaciel.config.CoreLibConfig;
import jakarta.annotation.Priority;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.Priorities;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerResponseContext;
import jakarta.ws.rs.container.PreMatching;
import jakarta.ws.rs.ext.Provider;
import java.io.IOException;
import java.util.UUID;
import org.jboss.logging.MDC;
import org.jboss.resteasy.reactive.server.ServerRequestFilter;
import org.jboss.resteasy.reactive.server.ServerResponseFilter;

@Provider
@PreMatching
@Priority(Priorities.USER)
@ApplicationScoped
public class HttpObservabilityHeaders {

  @Inject
  CoreLibConfig avPayConfig;

  @ServerRequestFilter
  public void getRequestFilter(ContainerRequestContext requestContext) throws IOException {
    if (!avPayConfig.traceabilityHeaders().enabled()) {
      return;
    }
    var traceId = requestContext.getHeaderString(ObservabilityHeaders.X_TRACE_ID.getHeaderName());
    var correlationId = requestContext.getHeaderString(ObservabilityHeaders.X_CORRELATION_ID.getHeaderName());

    if (traceId != null) {
      MDC.put(ObservabilityHeaders.X_TRACE_ID.getHeaderName(), traceId);
    } else {
      traceId = UUID.randomUUID().toString();
      MDC.put(ObservabilityHeaders.X_TRACE_ID.getHeaderName(), traceId);
    }

    if (correlationId != null) {
      MDC.put(ObservabilityHeaders.X_CORRELATION_ID.getHeaderName(), correlationId);
    } else {
      correlationId = UUID.randomUUID().toString();
      MDC.put(ObservabilityHeaders.X_CORRELATION_ID.getHeaderName(), correlationId);
    }

    // Add trace and correlation IDs to request context for later use
    requestContext.setProperty(ObservabilityHeaders.X_TRACE_ID.getHeaderName(), MDC.get(ObservabilityHeaders.X_TRACE_ID.getHeaderName()));
    requestContext.setProperty(ObservabilityHeaders.X_CORRELATION_ID.getHeaderName(), MDC.get(ObservabilityHeaders.X_CORRELATION_ID.getHeaderName()));
  }

  @ServerResponseFilter
  public void getResponseFilter(ContainerResponseContext responseContext) throws IOException {
    if (!avPayConfig.traceabilityHeaders().enabled()) {
      return;
    }
    // Retrieve trace and correlation IDs from request context
    var traceId = (String) MDC.get(ObservabilityHeaders.X_TRACE_ID.getHeaderName());
    var correlationId = (String) MDC.get(ObservabilityHeaders.X_CORRELATION_ID.getHeaderName());

    // Add headers to response
    if (traceId != null) {
      responseContext.getHeaders().add(ObservabilityHeaders.X_TRACE_ID.getHeaderName(), traceId);
    }
    if (correlationId != null) {
      responseContext.getHeaders().add(ObservabilityHeaders.X_CORRELATION_ID.getHeaderName(), correlationId);
    }

    // Remove trace and correlation IDs from MDC
    MDC.remove(ObservabilityHeaders.X_TRACE_ID.getHeaderName());
    MDC.remove(ObservabilityHeaders.X_CORRELATION_ID.getHeaderName());
  }
}
