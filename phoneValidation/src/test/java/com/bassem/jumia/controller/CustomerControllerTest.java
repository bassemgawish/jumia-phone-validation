package com.bassem.jumia.controller;

import com.bassem.jumia.entity.Customer;
import com.bassem.jumia.model.CustomerModel;
import com.bassem.jumia.repository.CustomerRepository;
import com.bassem.jumia.service.CustomerSerivce;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.event.annotation.BeforeTestMethod;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest
class CustomerControllerTest {

  @MockBean
  CustomerRepository customerRepository;

  @MockBean
  CustomerSerivce customerSerivce;

  @Autowired
  MockMvc mockMvc;

  List<CustomerModel> emptyCustomerList = new ArrayList<>();
  List<CustomerModel> customerList = new ArrayList<>();
  List<CustomerModel> validCustomerList = new ArrayList<>();
  List<CustomerModel> invalidCustomerList = new ArrayList<>();
  List<CustomerModel> countryCustomerList = new ArrayList<>();
  List<CustomerModel> validCountryCustomerList = new ArrayList<>();
  List<CustomerModel> invalidCountryCustomerList = new ArrayList<>();

  @BeforeEach
  void initialValues() {
    customerList.add(new CustomerModel(1, "Bassem Gawish", "Morocco", "(212)", "691933626",
        "valid"));
    customerList.add(new CustomerModel(3, "Khaled Hassan", "Morocco", "(212)", "6007989253",
        "not valid"));
    customerList.add(
        new CustomerModel(4, "Jason Richard", "Cameroon", "(237)", "697151594", "valid"));
    customerList.add(new CustomerModel(5, "Florencio Samuel", "Mozambique", "(258)", "847651504",
        "valid"));
    //Valid Customer List
    validCustomerList.add(
        new CustomerModel(5, "Florencio Samuel", "Mozambique", "(258)", "847651504",
            "valid"));
    validCustomerList.add(
        new CustomerModel(4, "Jason Richard", "Cameroon", "(237)", "697151594", "valid"));

    //Invalid Customer List
    invalidCustomerList.add(new CustomerModel(3, "Khaled Hassan", "Morocco", "(212)", "6007989253",
        "not valid"));
    invalidCustomerList.add(
        new CustomerModel(6, "Tanvi Sachdeva", "Mozambique", "(258)", "84330678235",
            "not valid"));
    invalidCustomerList.add(new CustomerModel(2, "Ahmed Hassan", "Morocco", "(212)", "698054317",
        "not valid"));

    countryCustomerList.add(new CustomerModel(1, "Bassem Gawish", "Morocco", "(212)", "691933626",
        "valid")); // valid  // morocco
    countryCustomerList.add(new CustomerModel(2, "Ahmed Hassan", "Morocco", "(212)", "698054317",
        "valid")); // valid  // morocco
    countryCustomerList.add(new CustomerModel(3, "Khaled Hassan", "Morocco", "(212)", "6007989253",
        "not valid"));

    validCountryCustomerList.add(
        new CustomerModel(1, "Bassem Gawish", "Morocco", "(212)", "691933626",
            "valid")); // valid  // morocco
    validCountryCustomerList.add(
        new CustomerModel(2, "Ahmed Hassan", "Morocco", "(212)", "698054317",
            "valid")); // valid  // morocco
    invalidCountryCustomerList.add(
        new CustomerModel(6, "Tanvi Sachdeva", "Mozambique", "(258)", "84330678235",
            "not valid"));
    invalidCountryCustomerList.add(
        new CustomerModel(7, "Solo Dolo", "Mozambique", "(258)", "042423566",
            "not valid"));

  }

  @Test
  void getCustomers_shouldReturnEmptyList() throws Exception {
    Mockito.when(customerSerivce.getAllInternationalCustomer(null)).thenReturn(emptyCustomerList);
    mockMvc.perform(MockMvcRequestBuilders.get("/customer"))
        .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$").isEmpty());
  }

  @Test
  void getCustomers_shouldReturnCustomerList() throws Exception {
    Mockito.when(customerSerivce.getAllInternationalCustomer(null)).thenReturn(customerList);
    mockMvc.perform(MockMvcRequestBuilders.get("/customer"))
        .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(4)));

  }

  @Test
  void getCustomers_shouldReturnValidCustomerList() throws Exception {
    Mockito.when(customerSerivce.getAllInternationalCustomer("valid"))
        .thenReturn(validCustomerList);
    mockMvc.perform(MockMvcRequestBuilders.get("/customer").param("state", "valid"))
        .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)));
  }

  @Test
  void getCustomers_shouldReturnInvalidCustomerList() throws Exception {
    Mockito.when(customerSerivce.getAllInternationalCustomer("not valid"))
        .thenReturn(invalidCustomerList);
    mockMvc.perform(MockMvcRequestBuilders.get("/customer").param("state", "not valid"))
        .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(3)));
  }

  @Test
  void getCustomersByCountryName_shouldReturnCustomerList() throws Exception {
    Mockito.when(customerSerivce.filterInternationalCustomerByCountryAndState("Morocco", null))
        .thenReturn(countryCustomerList);
    mockMvc.perform(MockMvcRequestBuilders.get("/customer").param("countryName", "Morocco"))
        .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(3)))
        .andExpect(MockMvcResultMatchers.jsonPath("$[0].country", Matchers.is("Morocco")))
        .andExpect(MockMvcResultMatchers.jsonPath("$[1].country", Matchers.is("Morocco")))
        .andExpect(MockMvcResultMatchers.jsonPath("$[2].country", Matchers.is("Morocco")));
  }

  @Test
  void getCustomersByCountryName_shouldReturnValidCustomerList() throws Exception {
    Mockito.when(customerSerivce.filterInternationalCustomerByCountryAndState("Morocco", "valid"))
        .thenReturn(validCountryCustomerList);
    mockMvc.perform(MockMvcRequestBuilders.get("/customer").param("countryName", "Morocco")
            .param("state", "valid"))
        .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)))
        .andExpect(MockMvcResultMatchers.jsonPath("$[0].country", Matchers.is("Morocco")))
        .andExpect(MockMvcResultMatchers.jsonPath("$[0].state", Matchers.is("valid")))
        .andExpect(MockMvcResultMatchers.jsonPath("$[1].country", Matchers.is("Morocco")))
        .andExpect(MockMvcResultMatchers.jsonPath("$[1].state", Matchers.is("valid")));
  }

  @Test
  void getCustomersByCountryName_shouldReturnInvalidCustomerList() throws Exception {
    Mockito.when(customerSerivce.filterInternationalCustomerByCountryAndState("Mozambique", "not valid"))
        .thenReturn(invalidCountryCustomerList);
    mockMvc.perform(MockMvcRequestBuilders.get("/customer").param("countryName", "Mozambique")
            .param("state", "not valid"))
        .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)))
        .andExpect(MockMvcResultMatchers.jsonPath("$[0].country", Matchers.is("Mozambique")))
        .andExpect(MockMvcResultMatchers.jsonPath("$[0].state", Matchers.is("not valid")))
        .andExpect(MockMvcResultMatchers.jsonPath("$[1].country", Matchers.is("Mozambique")))
        .andExpect(MockMvcResultMatchers.jsonPath("$[1].state", Matchers.is("not valid")));
  }

}
