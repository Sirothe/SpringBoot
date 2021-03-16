import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Order } from '../model/order';

@Injectable({
  providedIn: 'root'
})
export class OrderService {
  private apiServerUrl=environment.apiBaseUrl;

  constructor(private http:HttpClient) { }

  public getOrders(): Observable<Order[]> {
    return this.http.get<Order[]>(`${this.apiServerUrl}/order/all`);
  }

  public addOrder(order: Order): Observable<Order> {
    return this.http.post<Order>(`${this.apiServerUrl}/order`, order);
  }

  public updateOrder(order: Order): Observable<Order> {
    return this.http.put<Order>(`${this.apiServerUrl}/order/update`, order);
  }

  public deleteOrder(orderID: number): Observable<void> {
    return this.http.delete<void>(`${this.apiServerUrl}/order/${orderID}`);
  }
}
