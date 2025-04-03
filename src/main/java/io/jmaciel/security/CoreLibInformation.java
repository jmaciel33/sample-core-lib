package io.jmaciel.security;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.jboss.logging.MDC;

public class CoreLibInformation implements CoreLibInformationValidation {

  private Map<String, Object> claims = (Map<String, Object>) MDC.get("token-claims");

  @Override
  public String getPlatformAccountId() {
    Object clientTenants = claims.get("account_id");
    if (clientTenants instanceof String accountId) {
      // Supondo que todos os elementos da lista são strings
      if (!accountId.isEmpty()) {
        return accountId;
      }
    }
    return null;
  }

  @Override
  public List<String> getClientTenantIds() {
    Object clientTenants = claims.get("client_tenants");

    if (clientTenants instanceof List<?>) {
      // Supondo que todos os elementos da lista são strings
      List<?> clientTenantsList = (List<?>) clientTenants;
      if (!clientTenantsList.isEmpty()) {
        return (List<String>) clientTenantsList;
      }
    }

    return new ArrayList<>(); // Retorna uma lista vazia em caso de falha
  }

  @Override
  public List<Integer> getUserTenantIds() {
    Object userTenants = claims.get("user_tenants");

    if (userTenants instanceof List<?>) {
      // Supondo que todos os elementos da lista são strings
      List<?> userTenantsList = (List<?>) userTenants;
      if (!userTenantsList.isEmpty()) {
        return (List<Integer>) userTenantsList;
      }
    }

    return new ArrayList<>(); // Retorna uma lista vazia em caso de falha
  }

  @Override
  public boolean isTenantValid(String tenantId) {
    List<String> clientTenantIds = getClientTenantIds();
    List<Integer> userTenantIds = getUserTenantIds();

    if (clientTenantIds.contains("*")) {
      if (userTenantIds != null && !userTenantIds.isEmpty()) {
        return userTenantIds.contains(Integer.valueOf(tenantId));
      }
      return true;
    } else {
      if (clientTenantIds.contains(tenantId)) {
        if (userTenantIds != null && !userTenantIds.isEmpty()) {
          return userTenantIds.contains(Integer.valueOf(tenantId));
        }
        return true;
      }
    }
    return false;
  }
}
