package com.bassem.jumia.model;

public class CustomerModel {

  private Integer id;
  private String name;
  private String country;
  private String countryCode;
  private String phoneNumber;
  private String state;

  public CustomerModel() {
  }

  public CustomerModel(Integer id, String name, String country, String countryCode,
                       String phoneNumber, String state) {
    this.id = id;
    this.name = name;
    this.country = country;
    this.countryCode = countryCode;
    this.phoneNumber = phoneNumber;
    this.state = state;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public String getCountryCode() {
    return countryCode;
  }

  public void setCountryCode(String countryCode) {
    this.countryCode = countryCode;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }
}
