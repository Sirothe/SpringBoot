import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Car } from '../model/car';
import { CarService } from '../_service/car.service';

@Component({
  selector: 'app-cars',
  templateUrl: './cars.component.html',
  styleUrls: ['./cars.component.css']
})
export class CarsComponent implements OnInit {
  public cars:Car[];
  public pageNow:number;

  constructor(private CarService:CarService) { }

  ngOnInit(): void {
    this.getCars(1);
    this.pageNow=1;
  }

  public getCars(page:number):void {
    this.CarService.getCars(page).subscribe(
      (response: Car[]) => {
        this.cars=response;
      },
      (error:HttpErrorResponse) => {
        alert(error.message);
      }
    )
  }

  isAdmin():boolean {
    if(sessionStorage.getItem('roles') === "[ROLE_ADMIN]") {
      return true;
    } else {
      return false;
    }
  }

  NextPage():void {
    if(sessionStorage.getItem('maxPage')==String(this.pageNow+1)) {
      this.pageNow=this.pageNow+1;
      this.getCars(this.pageNow);
      (<HTMLInputElement> document.getElementById("btnNextPage")).disabled = true;
      (<HTMLInputElement> document.getElementById("btnPrevPage")).disabled = false;
    }
  }
  
  PrevPage():void {
    if(this.pageNow-1==1) {
      this.pageNow=this.pageNow-1;
      this.getCars(this.pageNow);
      (<HTMLInputElement> document.getElementById("btnPrevPage")).disabled = true;
      (<HTMLInputElement> document.getElementById("btnNextPage")).disabled = false;
    }
  }
}
