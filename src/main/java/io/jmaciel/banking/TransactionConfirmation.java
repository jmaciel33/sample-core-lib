package io.jmaciel.banking;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionConfirmation {

  private String transactionId;
  private String idempotenceId;
  private String tenantId;

}
