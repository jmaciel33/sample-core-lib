package io.jmaciel.config;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.List;
import java.util.Optional;

public class NotEmptyOptionalListValidator implements ConstraintValidator<NotEmptyOptional, Optional<List<String>>> {

  @Override
  public boolean isValid(Optional<List<String>> value, ConstraintValidatorContext context) {
    return value.isPresent() && !value.get().isEmpty();
  }
}
