package com.bassem.jumia.service;


import com.bassem.jumia.entity.Customer;
import com.bassem.jumia.model.CustomerModel;
import com.bassem.jumia.model.InternationalCountryCode;
import com.bassem.jumia.repository.CustomerRepository;
import com.bassem.jumia.validators.RegexValidator;
import com.bassem.jumia.validators.StringValidator;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CustomerServiceImpl implements CustomerSerivce {

  @Autowired
  private CustomerRepository customerRepository;

  @Override
  public List<CustomerModel> getAllInternationalCustomer(String state) {
    final Map<String, InternationalCountryCode> internationalCountryCodeMap =
        new InternationalCountryCode().getInternationalCountries();
    final List<Customer> customers = customerRepository.findAll();
    if (!customers.isEmpty()) {
      StringValidator validator = new RegexValidator();
      List<CustomerModel> customerModels = new ArrayList<>();
      customers.forEach(customer -> {
        String code = customer.getPhone().split(" ")[0];
        if (internationalCountryCodeMap.containsKey(code)) {
          InternationalCountryCode country = internationalCountryCodeMap.get(code);
          CustomerModel customerModel = getCustomerModel(customer, country);
          if (state == null || validator.isEqual(state, customerModel.getState())) {
            customerModels.add(customerModel);
          }
        }
      });
      return customerModels;
    } else {
      return null;
    }

  }

  @Override
  public List<CustomerModel> filterInternationalCustomerByCountryAndState(String countryName,
                                                                          String state) {
    final InternationalCountryCode countryCode =
        new InternationalCountryCode().getCountryInternationalCode(countryName);
    final List<Customer> customers = customerRepository.findAll();
    StringValidator validator = new RegexValidator();
    if (!customers.isEmpty()) {
      List<CustomerModel> customerModels = new ArrayList<>();
      for (Customer customer : customers) {
        String code = customer.getPhone().split(" ")[0];
        if (validator.isEqual(countryCode.getCode(), code)) {
          CustomerModel customerModel = getCustomerModel(customer, countryCode);
          if (state == null || validator.isEqual(state, customerModel.getState())) {
            customerModels.add(customerModel);
          }
        }
      }
      return customerModels;
    } else {
      // TODO Throw if no data
    }


    return null;
  }

  private CustomerModel getCustomerModel(Customer customer,
                                         InternationalCountryCode internationalCountryCode) {
    final StringValidator regexValidator = new RegexValidator();
    String phoneNumber = customer.getPhone().split(" ")[1];
    CustomerModel customerModel = new CustomerModel();
    customerModel.setId(customer.getId());
    customerModel.setPhoneNumber(customer.getPhone());
    customerModel.setName(customer.getName());
    customerModel.setPhoneNumber(phoneNumber);
    customerModel.setCountry(internationalCountryCode.getName());
    customerModel.setCountryCode(internationalCountryCode.getCode());
    if (regexValidator.matchPattern(internationalCountryCode.getRegex(), customer.getPhone())) {
      customerModel.setState("valid");
    } else {
      customerModel.setState("not valid");
    }
    return customerModel;
  }

}
