package io.jmaciel.platform;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public enum TransactionType {
  PIX(1, "PIX", "Debit entry for internal transactions"),
  PIX_VENCIMENTO(2, "PIX_VENCIMENTO", "Debit entry for internal transactions with due date"),
  BOLETO(3, "BOLETO", "Credit entry for internal transactions");

  private final int code;
  private final String name;
  private final String description;

  public static TransactionType fromCode(int code) {
    for (TransactionType type : TransactionType.values()) {
      if (type.getCode() == code) {
        return type;
      }
    }
    throw new IllegalArgumentException("Unknown code: " + code);
  }
}