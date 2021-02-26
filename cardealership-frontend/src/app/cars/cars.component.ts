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

  constructor(private CarService:CarService) { }

  ngOnInit(): void {
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
}
