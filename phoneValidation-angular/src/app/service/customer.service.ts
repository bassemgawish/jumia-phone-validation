import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Customer } from '../models/customer.model';

@Injectable({
  providedIn: 'root',
})
export class CustomerService {
  private readonly REST_API = environment.api_url;
  private readonly SERVICE = '/customer';

  constructor(private httpClient: HttpClient) {}

  getAllCustomers(): Observable<Customer[]> {
    return this.httpClient.get<Customer[]>(this.REST_API + this.SERVICE);
  }

  filterCustomer(params: HttpParams): Observable<Customer[]> {
    return this.httpClient.get<Customer[]>(this.REST_API + this.SERVICE, {
      params: params,
    });
  }
}
