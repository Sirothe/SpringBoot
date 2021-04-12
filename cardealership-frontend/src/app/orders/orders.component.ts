import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
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
  public pageNow:number;
  public namefield:string;
  public search:boolean;
  public optionCars:Car[];
  public optionClients:Client[];
  public car:Car;
  public client:Client;
  public editOrder:Order;
  public deleteOrder:Order;

  constructor(private OrderService:OrderService,private CarService:CarService,private ClientService:ClientService) { }

  ngOnInit(): void {
    this.getOrders(1);
    this.pageNow=1;
  }

  public getOrders(page:number):void {
    this.OrderService.getOrders(page).subscribe(
      (response: Order[]) => {
        this.orders=response;
      },
      (error:HttpErrorResponse) => {
        alert(error.message);
      }
    )
  }

  public getOrdersByClientName(page:number,name:string):void {
    this.OrderService.getOrdersByClientName(page,name).subscribe(
      (response: Order[]) => {
        this.orders=response;
      },
      (error:HttpErrorResponse) => {
        alert(error.message);
      }
    )
  }

  public getOrdersByCarName(page:number,name:string):void {
    this.OrderService.getOrdersByCarName(page,name).subscribe(
      (response: Order[]) => {
        this.orders=response;
      },
      (error:HttpErrorResponse) => {
        alert(error.message);
      }
    )
  }

  public getOptionsCarByName(name:string):void {
    this.CarService.getAllCarsByName(name).subscribe(
      (response: Car[]) => {
        this.optionCars=response;
      },
      (error:HttpErrorResponse) => {
        alert(error.message);
      }
    )
  }

  public getOptionsClientsByName(name:string):void {
    this.ClientService.getAllClientsByName(name).subscribe(
      (response: Client[]) => {
        this.optionClients=response;
      },
      (error:HttpErrorResponse) => {
        alert(error.message);
      }
    )
  }

  searchCheckClientName():void {
    this.namefield = (<HTMLInputElement>document.getElementById("inSearch")).value;
    if(this.namefield!="") {
      this.search=false;
      this.pageNow=1;
      this.getOrdersByClientName(this.pageNow,this.namefield);
    } else {
      this.search=true;
      this.pageNow=1;
      this.getOrders(this.pageNow);
    }
  }

  searchCheckCarName():void {
    this.namefield = (<HTMLInputElement>document.getElementById("inSearch")).value;
    if(this.namefield!="") {
      this.search=false;
      this.pageNow=1;
      this.getOrdersByCarName(this.pageNow,this.namefield);
    } else {
      this.search=true;
      this.pageNow=1;
      this.getOrders(this.pageNow);
    }
  }

  NextPage():void {
    if(sessionStorage.getItem('maxPage')==String(this.pageNow+1)) {
      this.pageNow=this.pageNow+1;
      this.getOrders(this.pageNow);
      (<HTMLInputElement> document.getElementById("btnNextPage")).disabled = true;
      (<HTMLInputElement> document.getElementById("btnPrevPage")).disabled = false;
    }
  }
  
  PrevPage():void {
    if(this.pageNow-1==1) {
      this.pageNow=this.pageNow-1;
      this.getOrders(this.pageNow);
      (<HTMLInputElement> document.getElementById("btnPrevPage")).disabled = true;
      (<HTMLInputElement> document.getElementById("btnNextPage")).disabled = false;
    }
  }

  PageReset():void {
    this.getOrders(1);
    this.pageNow=1;
    this.search=false;
    (<HTMLInputElement> document.getElementById("btnNextPage")).disabled = false;
    (<HTMLInputElement> document.getElementById("btnPrevPage")).disabled = true;
  }

  public onOpenModal(order:Order,mode:string):void {
    const container = document.getElementById('container-buttons');
    const button = document.createElement('button');
    button.type = 'button';
    button.style.display = 'none';
    button.setAttribute('data-toggle','modal');
    if(mode === 'add') {
      this.getOptionsCarByName("");
      this.getOptionsClientsByName("");
      button.setAttribute('data-target','#addOrderModal');
    } else if (mode === 'edit') {
      this.getOptionsCarByName("");
      this.getOptionsClientsByName("");
      this.editOrder = order;
      button.setAttribute('data-target','#editOrderModal');
    } else if (mode === 'delete') {
      this.deleteOrder = order;
      button.setAttribute('data-target','#deleteOrderModal');
    }
    container.appendChild(button);
    button.click();
  }

  public onAddOrder(addForm:NgForm):void {
    document.getElementById("close-form-order-add").click();
    this.OrderService.addOrder(addForm.value).subscribe((resp:Order) => {
      this.PageReset();
    },
    (error:HttpErrorResponse) => {
      alert(error.message);
    })
  }

  public onUpdateOrder(editForm:NgForm):void {
    document.getElementById("close-form-order-edit").click();
    editForm.setValue({orderId:this.editOrder.orderId,client:this.editOrder.client,car:editForm.value.car,status:editForm.value.status});
    this.OrderService.updateOrder(editForm.value).subscribe((resp:Order) => {
      this.PageReset();
    },
    (error:HttpErrorResponse) => {
      alert(error.message);
    })
  }

  public onDeleteOrder(orderId:number):void {
    document.getElementById("close-form-order-delete").click();
    this.OrderService.deleteOrder(orderId).subscribe((resp:void) => {
      this.PageReset();
    },
    (error:HttpErrorResponse) => {
      alert(error.message);
    })
  }
}
