package com.bassem.jumia.service;

import com.bassem.jumia.model.CustomerModel;
import java.util.List;

public interface CustomerSerivce {


  List<CustomerModel> getAllInternationalCustomer(String state);

  List<CustomerModel> filterInternationalCustomerByCountryAndState(String countryName,
                                                                   String state);


}
