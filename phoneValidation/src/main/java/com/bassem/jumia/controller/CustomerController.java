package com.bassem.jumia.controller;

import com.bassem.jumia.model.CustomerModel;
import com.bassem.jumia.service.CustomerSerivce;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class CustomerController {


  @Autowired
  private CustomerSerivce customerSerivce;


  @GetMapping("/customer")
  public ResponseEntity getInternationalCustomer(
      @RequestParam(value = "countryName", required = false) String countryName,
      @RequestParam(value = "state", required = false) String state) {
    if (countryName == null) {
      List<CustomerModel> customerModels =
          customerSerivce.getAllInternationalCustomer(state);
      return ResponseEntity.ok(customerModels);
    } else {
      List<CustomerModel> customerModels =
          customerSerivce.filterInternationalCustomerByCountryAndState(countryName, state);
      return ResponseEntity.ok(customerModels);
    }

  }
}
