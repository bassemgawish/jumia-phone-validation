import { HttpClient, HttpParams } from '@angular/common/http';
import { Component, Input, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup } from '@angular/forms';
import { environment } from 'src/environments/environment';
import { CustomerSearch } from '../models/customer-search';
import { Customer } from '../models/customer.model';
import { CustomerService } from '../service/customer.service';

@Component({
  selector: 'app-country-phones',
  templateUrl: './country-phones.component.html',
  styleUrls: ['./country-phones.component.css'],
})
export class CountryPhonesComponent implements OnInit {
  private readonly REST_API = environment.api_url;
  customerList: Customer[] = [];

  countries: string[] = [
    '',
    'Cameroon',
    'Ethiopia',
    'Morocco',
    'Mozambique',
    'Uganda',
  ];
  searchForm = new FormControl('');
  search = new CustomerSearch('', '');
  constructor(private customerService: CustomerService) {}

  ngOnInit(): void {
    this.loadCustomerPhones();
  }

  loadCustomerPhones(): void {
    this.customerService.getAllCustomers().subscribe((data) => {
      this.customerList = data;
    });
  }

  onSubmit() {
    let params = new HttpParams();
    if (this.search.countryName != '') {
      params = params.append('countryName', this.search.countryName);
    }
    if (this.search.state != '') {
      params = params.append('state', this.search.state);
    }
    this.customerService.filterCustomer(params).subscribe((data) => {
      console.log('data recieved');
      this.customerList = data;
    });
    // this.httpClient
    //   .get<CustomerModel[]>('http://localhost:8080/jumia/customer', {
    //     params: params,
    //   })
    //   .subscribe((data) => {
    //     console.log('data recieved');
    //     this.customerList = data;
    //   });
  }
}
