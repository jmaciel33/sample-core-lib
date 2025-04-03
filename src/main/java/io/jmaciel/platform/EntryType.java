package io.jmaciel.platform;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public enum EntryType {
  DEBIT(1, "DEBIT", "Debit entry for internal transactions"),
  CREDIT(2, "CREDIT", "Credit entry for internal transactions"),
  EXTERNAL(3, "EXTERNAL", "Entry for external transactions");

  private final int code;
  private final String name;
  private final String description;

  public static EntryType fromCode(int code) {
    for (EntryType type : EntryType.values()) {
      if (type.getCode() == code) {
        return type;
      }
    }
    throw new IllegalArgumentException("Unknown code: " + code);
  }

}