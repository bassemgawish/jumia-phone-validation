package com.bassem.jumia.service;

import com.bassem.jumia.entity.Customer;
import com.bassem.jumia.repository.CustomerRepository;
import java.util.ArrayList;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class CustomerServiceImplTest {

  @MockBean
  CustomerRepository customerRepository;

  @Autowired
  CustomerSerivce customerSerivce;

  List<Customer> emptyList = new ArrayList<>();
  List<Customer> customerList = new ArrayList<>();
  List<Customer> validCustomerList = new ArrayList<>();
  List<Customer> invalidCustomerList = new ArrayList<>();
  List<Customer> validMoroccoCustomerList = new ArrayList<>();
  List<Customer> invalidMoroccoCustomerList = new ArrayList<>();

  @BeforeEach
  void initialValues() {
    customerList.add(new Customer(1, "Bassem Gawish", "(212) 6007989253"));
    customerList.add(new Customer(2, "Ahmed Hassan", "(212) 691933626"));
    customerList.add(new Customer(3, "Khaled Hassan", "(258) 847651504"));

    validCustomerList.add(new Customer(1, "Jason Richard", "(212) 698054317"));
    validCustomerList.add(new Customer(2, "Nada Sofie", "(256) 714660221"));
    validCustomerList.add(new Customer(3, "Ezequiel Fenias", "(251) 911168450"));
    validCustomerList.add(new Customer(4, "Mohamed hesham", "(258) 84330678235"));

    invalidCustomerList.add(new Customer(1, "Ezequiel Fenias", "(258) 84330678235"));
    invalidCustomerList.add(new Customer(2, "Ezequiel Fenias", "(256) 3142345678"));
    invalidCustomerList.add(new Customer(3, "Ezequiel Fenias", "(237) 6A0311634"));
    invalidCustomerList.add(new Customer(4, "Ezequiel Fenias", "(251) 911168450"));

    validMoroccoCustomerList.add(new Customer(1, "Bassem Gawish", "(212) 698054317"));
    validMoroccoCustomerList.add(new Customer(2, "Ahmed Hassan", "(212) 633963130"));
    validMoroccoCustomerList.add(new Customer(3, "Khaled Hassan", "(212) 6546545369"));

    invalidMoroccoCustomerList.add(new Customer(1, "Bassem Gawish", "(212) 6007989253"));
    invalidMoroccoCustomerList.add(new Customer(2, "Ahmed Hassan", "(212) 698054317"));
    invalidMoroccoCustomerList.add(new Customer(3, "Khaled Hassan", "(212) 691933626"));
  }

  @Test
  public void getCustomer_shouldReturnEmpty() {
//    Mockito.when(customerRepository.findAll()).thenReturn(emptyList);
    Mockito.when(customerRepository.findAll()).thenReturn(emptyList);
    Assertions.assertThat(customerSerivce.getAllInternationalCustomer(null)).isEqualTo(null);
  }

  @Test
  public void getCustomer_shouldReturnList() {
    Mockito.when(customerRepository.findAll()).thenReturn(customerList);
    Assertions.assertThat(customerSerivce.getAllInternationalCustomer(null).size()).isEqualTo(3);
  }

  @Test
  public void getCustomerByState_shouldReturnInValidResult() {
    Mockito.when(customerRepository.findAll()).thenReturn(invalidCustomerList);
    Assertions.assertThat(customerSerivce.getAllInternationalCustomer("not valid"))
        .filteredOn(customer ->
            customer.getState().equals("not valid")).size().isEqualTo(3);

  }


  @Test
  public void getCustomerByState_shouldReturnValidResult() {
    Mockito.when(customerRepository.findAll()).thenReturn(validCustomerList);
    Assertions.assertThat(customerSerivce.getAllInternationalCustomer("valid"))
        .filteredOn(customer -> customer.getState().equals("valid")).size().isEqualTo(3);
  }

  @Test
  public void getCustomerByCountryName_shouldReturnValidCustomerResult() {
    Mockito.when(customerRepository.findAll()).thenReturn(validMoroccoCustomerList);
    Assertions.assertThat(
            customerSerivce.filterInternationalCustomerByCountryAndState("Morocco", "valid")).size()
        .isEqualTo(2);

  }

  @Test
  public void getCustomerByCountryName_shouldReturnInValidResult() {
    Mockito.when(customerRepository.findAll()).thenReturn(invalidMoroccoCustomerList);
    Assertions.assertThat(
            customerSerivce.filterInternationalCustomerByCountryAndState("Morocco", "not valid")).size()
        .isEqualTo(1);
  }

}
