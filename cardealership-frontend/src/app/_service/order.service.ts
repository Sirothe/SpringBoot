import { HttpClient, HttpResponse } from '@angular/common/http';
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

  public getOrders(pageNumber:number): Observable<Order[]> {
    this.http.get<Order[]>(`${this.apiServerUrl}/order/p=${pageNumber}`,{observe: 'response'})
    .subscribe(resp => this.headerSaver(resp));
    return this.http.get<Order[]>(`${this.apiServerUrl}/order/p=${pageNumber}`);
  }

  public getOrdersByClientName(pageNumber:number,name:string):Observable<Order[]> {
    this.http.get<Order[]>(`${this.apiServerUrl}/order/clinm=${name}/p=${pageNumber}`,{observe: 'response'})
    .subscribe(resp => this.headerSaver(resp));
    return this.http.get<Order[]>(`${this.apiServerUrl}/order/clinm=${name}/p=${pageNumber}`);
  }

  public getOrdersByCarName(pageNumber:number,name:string):Observable<Order[]> {
    this.http.get<Order[]>(`${this.apiServerUrl}/order/carnm=${name}/p=${pageNumber}`,{observe: 'response'})
    .subscribe(resp => this.headerSaver(resp));
    return this.http.get<Order[]>(`${this.apiServerUrl}/order/carnm=${name}/p=${pageNumber}`);
  }

  public addOrder(order: Order): Observable<Order> {
    return this.http.post<Order>(`${this.apiServerUrl}/order`, order);
  }

  public updateOrder(order: Order): Observable<Order> {
    return this.http.put<Order>(`${this.apiServerUrl}/order`, order);
  }

  public deleteOrder(orderID: number): Observable<void> {
    return this.http.delete<void>(`${this.apiServerUrl}/order/${orderID}`);
  }

  public headerSaver(data:HttpResponse<Object>):void {
    sessionStorage.setItem('maxPage',data.headers.get('pagemax'));
    sessionStorage.setItem('totalItems',data.headers.get('totalitems'));
  }
}
