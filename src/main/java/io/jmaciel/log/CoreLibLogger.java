package io.jmaciel.log;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jmaciel.traceability.ObservabilityHeaders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

public class CoreLibLogger {

  private static final ObjectMapper objectMapper = new ObjectMapper();
  private final Logger logger;
  private final String className;

  private CoreLibLogger(Class<?> clazz) {
    this.logger = LoggerFactory.getLogger(clazz);
    this.className = clazz.getSimpleName();
  }

  public static CoreLibLogger getLogger(Class<?> clazz) {
    return new CoreLibLogger(clazz);
  }

  public void info(String message, Object... args) {
    String formattedMessage = String.format(message, args);
    CustomLogMessage logMessage = new CustomLogMessage(
        "INFO",
        className,
        formattedMessage,
        MDC.get(ObservabilityHeaders.X_TRACE_ID.getHeaderName()),
        MDC.get(ObservabilityHeaders.X_CORRELATION_ID.getHeaderName())
    );
    logger.info(convertToJson(logMessage));
  }

  public void error(String message, Throwable throwable, Object... args) {
    String formattedMessage = String.format(message, args);
    CustomLogMessage logMessage = new CustomLogMessage(
        "ERROR",
        className,
        formattedMessage,
        MDC.get(ObservabilityHeaders.X_TRACE_ID.getHeaderName()),
        MDC.get(ObservabilityHeaders.X_CORRELATION_ID.getHeaderName())
    );
    logger.error(convertToJson(logMessage), throwable);
  }

  public void debug(String message, Object... args) {
    String formattedMessage = String.format(message, args);
    CustomLogMessage logMessage = new CustomLogMessage(
        "DEBUG",
        className,
        formattedMessage,
        MDC.get(ObservabilityHeaders.X_TRACE_ID.getHeaderName()),
        MDC.get(ObservabilityHeaders.X_CORRELATION_ID.getHeaderName())
    );
    logger.debug(convertToJson(logMessage));
  }

  private String convertToJson(CustomLogMessage logMessage) {
    try {
      return objectMapper.writeValueAsString(logMessage);
    } catch (JsonProcessingException e) {
      logger.error("Failed to convert log message to JSON", e);
      return "{}";
    }
  }
}
