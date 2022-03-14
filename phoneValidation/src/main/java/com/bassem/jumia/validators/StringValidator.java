package com.bassem.jumia.validators;

public interface StringValidator {


  default boolean isEqual(String x, String y) {
    return x.equals(y);
  }

  boolean matchPattern(String pattern, String str);
}
