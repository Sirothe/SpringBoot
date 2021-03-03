import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { error } from 'protractor';
import { Order } from '../model/order';
import { OrderService } from '../_service/order.service';

@Component({
  selector: 'app-orders',
  templateUrl: './orders.component.html',
  styleUrls: ['./orders.component.css']
})
export class OrdersComponent implements OnInit {
  public orders:Order[];

  constructor(private OrderService:OrderService) { }

  ngOnInit(): void {
    this.getOrders();
  }

  public getOrders():void {
    this.OrderService.getOrders().subscribe(
      (response: Order[]) => {
        this.orders=response;
      },
      (error:HttpErrorResponse) => {
        alert(error.message);
      }
    )
  }
}
