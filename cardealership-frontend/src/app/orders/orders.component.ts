import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { error } from 'protractor';
import { Car } from '../model/car';
import { Client } from '../model/client';
import { Order } from '../model/order';
import { CarService } from '../_service/car.service';
import { ClientService } from '../_service/client.service';
import { OrderService } from '../_service/order.service';

@Component({
  selector: 'app-orders',
  templateUrl: './orders.component.html',
  styleUrls: ['./orders.component.css']
})
export class OrdersComponent implements OnInit {
  public orders:Order[];
  public cars:Car[];
  public clients:Client[];

  constructor(private OrderService:OrderService,private CarService:CarService,private ClientService:ClientService) { }

  ngOnInit(): void {
    this.getOrders();
    this.getCars();
    this.getClients();
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
  public getCars():void {
    this.CarService.getCars().subscribe(
      (response: Car[]) => {
        this.cars=response;
      },
      (error:HttpErrorResponse) => {
        alert(error.message);
      }
    )
  }
  public getClients():void {
    this.ClientService.getClients().subscribe(
      (response: Client[]) => {
        this.clients=response;
      },
      (error:HttpErrorResponse) => {
        alert(error.message);
      }
    )
  }
}
