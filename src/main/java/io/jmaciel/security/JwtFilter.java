package io.jmaciel.security;

import io.jmaciel.config.CoreLibConfig;
import io.jmaciel.exception.ArchitectureException;
import io.jmaciel.exception.CustomErrorMessage;
import io.jmaciel.exception.CustomErrorResponse;
import io.jmaciel.exception.CustomExceptionResponse;
import jakarta.annotation.Priority;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.Priorities;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerResponseContext;
import jakarta.ws.rs.container.ResourceInfo;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.ext.Provider;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.jboss.logging.MDC;
import org.jboss.resteasy.reactive.server.ServerRequestFilter;
import org.jboss.resteasy.reactive.server.ServerResponseFilter;

@Provider
@Priority(Priorities.AUTHENTICATION)
@ApplicationScoped
public class JwtFilter {

  @Context
  ResourceInfo resourceInfo;

  @Inject
  CoreLibConfig avPayConfig;

  @ServerRequestFilter
  public void getRequestFilter(ContainerRequestContext requestContext) {
    final List<CustomErrorMessage> errorMessages = new ArrayList<>();


    if (!avPayConfig.jwt().enabled()) {
      return;
    }

    if (!requiresAuth()) {
      return;
    }

    Optional<List<String>> configClaims = CoreLibConfig.DEFAULT_CLAIMS;

    if (!avPayConfig.jwt().claims().isEmpty()) {
      configClaims = avPayConfig.jwt().claims();
    }

    var authorization = requestContext.getHeaderString("Authorization");
    if (authorization != null && authorization.startsWith("Bearer ")) {
      authorization = authorization.replaceFirst("Bearer ", "");
    }

    var validToken = JwtUtils.isTokenValidOffline(authorization, avPayConfig.jwt().jwksUrl().orElseThrow());
    if(!validToken) {
      errorMessages.add(new CustomErrorMessage("Você não possui um token válido."));
      throw new ArchitectureException(new CustomExceptionResponse(
          new CustomErrorResponse("003", "Woops, esse token não é valido.",
              errorMessages)));
    }

    if (authorization != null) {
      Map<String, Object> claims = JwtUtils.decodeJWT(authorization, configClaims);
      MDC.put("token-claims", claims);
    }
  }

  @ServerResponseFilter
  public void getResponseFilter(ContainerResponseContext responseContext) throws IOException {
    if (!avPayConfig.jwt().enabled()) {
      return;
    }
    MDC.remove("token-claims");
  }

  private boolean requiresAuth() {

    if (resourceInfo.getResourceMethod().isAnnotationPresent(RequiresAuth.class)) {
      return true;
    }

    return resourceInfo.getResourceClass().isAnnotationPresent(RequiresAuth.class);
  }

}
