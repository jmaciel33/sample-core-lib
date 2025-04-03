package io.jmaciel.security;

import io.jmaciel.exception.ArchitectureException;
import io.jmaciel.exception.CustomErrorResponse;
import io.jmaciel.exception.CustomExceptionResponse;
import io.jmaciel.log.CoreLibLogger;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import javax.net.ssl.SSLContext;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager;
import org.apache.hc.client5.http.socket.ConnectionSocketFactory;
import org.apache.hc.client5.http.ssl.SSLConnectionSocketFactory;
import org.apache.hc.core5.http.config.RegistryBuilder;
import org.apache.hc.core5.ssl.SSLContextBuilder;
import org.apache.hc.core5.ssl.SSLContexts;
import org.apache.hc.core5.ssl.TrustStrategy;

public class HttpClientSSLConfig {

  static final CoreLibLogger logger = CoreLibLogger.getLogger(HttpClientSSLConfig.class);

  public static CloseableHttpClient createHttpClientWithPFX(String pfxFilePath, String pfxPassword) throws Exception {
    KeyStore keyStore = KeyStore.getInstance("PKCS12");
    try (FileInputStream keyStoreStream = new FileInputStream(pfxFilePath)) {
      keyStore.load(keyStoreStream, pfxPassword.toCharArray());
    }

    SSLContext sslContext = SSLContextBuilder.create()
        .loadKeyMaterial(keyStore, pfxPassword.toCharArray())
        .loadTrustMaterial((TrustStrategy) (X509Certificate[] chain, String authType) -> true)
        .build();

    SSLConnectionSocketFactory sslSocketFactory = new SSLConnectionSocketFactory(sslContext);
    PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager(
        RegistryBuilder.<ConnectionSocketFactory>create()
            .register("https", sslSocketFactory)
            .build()
    );

    return HttpClients.custom()
        .setConnectionManager(connectionManager)
        .build();
  }

  public static SSLContext createSSLContext(String pfxFilePath, String pfxPassword)
      throws ArchitectureException {
    try (FileInputStream fis = new FileInputStream(pfxFilePath)) {
      KeyStore keyStore = KeyStore.getInstance("PKCS12");
      keyStore.load(fis, pfxPassword.toCharArray());
      return SSLContexts.custom()
          .loadKeyMaterial(keyStore, pfxPassword.toCharArray())
          .build();
    } catch (IOException | NoSuchAlgorithmException | CertificateException | KeyStoreException |
             UnrecoverableKeyException | KeyManagementException e) {
      logger.error("Woops, erro gerar o SSLContext.", e.getCause(), e);
      throw new ArchitectureException(new CustomExceptionResponse(
          new CustomErrorResponse("005", "Woops, erro gerar o SSLContext.",
              new ArrayList<>())));
    }


  }
}
