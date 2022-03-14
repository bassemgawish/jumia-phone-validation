package com.bassem.jumia.validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexValidator implements StringValidator {

  @Override
  public boolean matchPattern(String regex, String str) {
    Pattern regexPattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
    Matcher matcher = regexPattern.matcher(str);
    return matcher.find();
  }
}
