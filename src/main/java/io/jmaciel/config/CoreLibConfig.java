package io.jmaciel.config;

import io.quarkus.runtime.annotations.ConfigRoot;
import io.smallrye.config.ConfigMapping;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.Optional;

@ConfigMapping(prefix = "core-lib")
@ConfigRoot
@ApplicationScoped
public interface CoreLibConfig {

  Optional<List<String>> DEFAULT_CLAIMS = Optional.of(List.of("client_tenants", "user_tenants", "account_id"));

  TraceabilityHeaders traceabilityHeaders();
  JwtConfig jwt();

  interface TraceabilityHeaders {
    boolean enabled();
  }

  interface JwtConfig {
    Optional<List<String>> claims();
    boolean enabled();
    Optional<String> jwksUrl();
  }
}
