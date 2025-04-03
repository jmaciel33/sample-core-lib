package io.jmaciel.security;

import com.auth0.jwk.Jwk;
import com.auth0.jwk.JwkException;
import com.auth0.jwk.JwkProvider;
import com.auth0.jwk.UrlJwkProvider;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import io.jmaciel.exception.ArchitectureException;
import io.jmaciel.exception.CustomErrorMessage;
import io.jmaciel.exception.CustomErrorResponse;
import io.jmaciel.exception.CustomExceptionResponse;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.interfaces.RSAPublicKey;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class JwtUtils {

  private JwtUtils() {
    throw new IllegalStateException("Utility class");
  }

  public static Map<String, Object> decodeJWT(String token, Optional<List<String>> claimsToDecode) {
    try {
      DecodedJWT jwt = JWT.decode(token);
      Map<String, Object> claims = new HashMap<>();

      if (claimsToDecode.isEmpty()) {
        throw new ArchitectureException(new CustomExceptionResponse(
            new CustomErrorResponse("003", "Architecture Error",
                List.of(new CustomErrorMessage("Claims can't be null.")))));
      }

      for (String claim : claimsToDecode.get()) {
        claims.put(claim, jwt.getClaim(claim).as(Object.class));
      }
      return claims;
    } catch (JWTDecodeException exception) {
      throw new ArchitectureException(new CustomExceptionResponse(
          new CustomErrorResponse("003", "Architecture Error",
              List.of(new CustomErrorMessage("Failed to decode JWT token.")))));
    }
  }

  public static boolean isTokenValidOffline(String token, String jwksUrl) {
    try {
      JwkProvider provider = new UrlJwkProvider(new URL(jwksUrl));

      DecodedJWT jwt = JWT.decode(token);
      Jwk jwk = provider.get(jwt.getKeyId());
      RSAPublicKey publicKey = (RSAPublicKey) jwk.getPublicKey();
      Algorithm algorithm = Algorithm.RSA256(publicKey, null);
      JWTVerifier verifier = JWT.require(algorithm).build();
      verifier.verify(token);

      return true;
    } catch (JWTVerificationException | IllegalArgumentException exception) {
      return false;
    } catch (JwkException | MalformedURLException e) {
      throw new ArchitectureException(new CustomExceptionResponse(
          new CustomErrorResponse("003", "Architecture Error",
              List.of(new CustomErrorMessage("Token Invalido.")))));
    }
  }
}
