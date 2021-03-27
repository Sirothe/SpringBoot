import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Order } from '../model/order';
import { OrderService } from '../_service/order.service';

@Component({
  selector: 'app-orders',
  templateUrl: './orders.component.html',
  styleUrls: ['./orders.component.css']
})
export class OrdersComponent implements OnInit {
  public orders:Order[];
  public pageNow:number;

  constructor(private OrderService:OrderService) { }

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
}
