package io.jmaciel.security;

import java.util.List;

public interface CoreLibInformationValidation {

  String getPlatformAccountId();

  List<String> getClientTenantIds();

  List<Integer> getUserTenantIds();

  boolean isTenantValid(String tenantId);

}
