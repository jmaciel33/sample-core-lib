package io.jmaciel.platform;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public enum TransactionStatus {
  ATIVA(1, "ATIVA", "The transaction is currently active and ongoing"),
  CONCLUIDA(2, "CONCLUIDA", "The transaction has been completed successfully, typically indicating a credit entry for internal transactions"),
  REMOVIDA_PELO_USUARIO_RECEBEDOR(3, "REMOVIDA_PELO_USUARIO_RECEBEDOR", "The transaction was removed by the receiving user, typically indicating a debit entry for internal transactions"),
  REMOVIDA_PELO_PSP(4, "REMOVIDA_PELO_PSP", "The transaction was removed by the Payment Service Provider (PSP), typically indicating a debit entry for internal transactions"),
  NAO_REALIZADO(5, "NÃO_REALIZADO", "The transaction was not completed, typically indicating a debit entry for internal transactions"),
  EM_PROCESSAMENTO(6, "EM_PROCESSAMENTO", "The transaction is currently being processed, typically indicating a debit entry for internal transactions"),
  FINALIZADO(7, "FINALIZADO", "The transaction has been finalized, typically indicating a debit entry for internal transactions"),
  REJEITADO(8, "REJEITADO", "The transaction was rejected, typically indicating a debit entry for internal transactions");

  private final int code;
  private final String name;
  private final String description;

  public static TransactionStatus fromCode(int code) {
    for (TransactionStatus type : TransactionStatus.values()) {
      if (type.getCode() == code) {
        return type;
      }
    }
    throw new IllegalArgumentException("Unknown code: " + code);
  }
}