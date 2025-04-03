package io.jmaciel.security;

import jakarta.ws.rs.client.ClientRequestContext;
import jakarta.ws.rs.client.ClientRequestFilter;
import java.io.IOException;

public class DynamicAuthHeaderRequestFilter implements ClientRequestFilter {
  private final String authToken;
  private final String clientId;

  public DynamicAuthHeaderRequestFilter(String authToken, String clientId) {
    this.authToken = authToken;
    this.clientId = clientId;
  }

  @Override
  public void filter(ClientRequestContext requestContext) throws IOException {
    requestContext.getHeaders().add("Authorization", "Bearer " + authToken);
    requestContext.getHeaders().add("clientId", clientId);
  }
}
