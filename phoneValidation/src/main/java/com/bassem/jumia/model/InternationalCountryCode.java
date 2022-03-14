package com.bassem.jumia.model;

import java.util.HashMap;
import java.util.Map;

public class InternationalCountryCode extends Country {

  private String code;

  private String regex;

  public InternationalCountryCode() {

  }

  public InternationalCountryCode(String name, String code, String regex) {
    super(name);
    this.code = code;
    this.regex = regex;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getRegex() {
    return regex;
  }

  public void setRegex(String regex) {
    this.regex = regex;
  }


  public Map<String, InternationalCountryCode> getInternationalCountries() {
    Map<String, InternationalCountryCode> internationalCountryCodeMap = new HashMap<>();
    internationalCountryCodeMap.put("(237)",
        new InternationalCountryCode("Cameroon", "(237)", "\\(237\\)\\ ?[2368]\\d{7,8}$"));
    internationalCountryCodeMap.put("(251)",
        new InternationalCountryCode("Ethiopia", "(251)", "\\(251\\)\\ ?[1-59]\\d{8}$"));
    internationalCountryCodeMap.put("(212)",
        new InternationalCountryCode("Morocco", "(212)", "\\(212\\)\\ ?[5-9]\\d{8}$"));
    internationalCountryCodeMap.put("(258)",
        new InternationalCountryCode("Mozambique", "(258)", "\\(258\\)\\ ?[28]\\d{7,8}$"));
    internationalCountryCodeMap.put("(256)",
        new InternationalCountryCode("Uganda", "(256)", "\\(256\\)\\ ?\\d{9}$"));
    return internationalCountryCodeMap;
  }

  //
  public InternationalCountryCode getCountryInternationalCode(String countryName) {
    if (countryName.equals("Cameroon")) {
      return new InternationalCountryCode("Cameroon", "(237)", "\\(237\\)\\ ?[2368]\\d{7,8}$");
    } else if (countryName.equals("Ethiopia")) {
      return new InternationalCountryCode("Ethiopia", "(251)", "\\(251\\)\\ ?[1-59]\\d{8}$");

    } else if (countryName.equals("Morocco")) {
      return new InternationalCountryCode("Morocco", "(212)", "\\(212\\)\\ ?[5-9]\\d{8}$");
    } else if (countryName.equals("Mozambique")) {
      return new InternationalCountryCode("Mozambique", "(258)", "\\(258\\)\\ ?[28]\\d{7,8}$");
    } else if (countryName.equals("Uganda")) {
      return new InternationalCountryCode("Uganda", "(256)", "\\(256\\)\\ ?\\d{9}$");
    } else {
      return null;
    }
  }
}
