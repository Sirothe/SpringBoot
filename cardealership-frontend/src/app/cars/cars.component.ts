import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { Car } from '../model/car';
import { CarService } from '../_service/car.service';

@Component({
  selector: 'app-cars',
  templateUrl: './cars.component.html',
  styleUrls: ['./cars.component.css']
})
export class CarsComponent implements OnInit {
  public cars: Car[];
  public pageNow: number;
  public search: boolean;
  public namefield: string;
  public editCar: Car;
  public deleteCar: Car;

  constructor(private CarService: CarService, private router: Router) { }

  ngOnInit(): void {
    this.getCars(1);
    this.pageNow = 1;
    this.search = false;
  }

  public getCars(page: number): void {
    this.CarService.getCars(page).subscribe(
      (response: Car[]) => {
        this.cars = response;
        if(Number(sessionStorage.getItem('maxPage'))==1) {
          (<HTMLInputElement>document.getElementById("btnNextPage")).disabled = true;
        }
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    )
  }

  public getCarsByName(page: number, name: string): void {
    this.CarService.getCarsbyName(page, name).subscribe(
      (response: Car[]) => {
        this.cars = response;
        if(Number(sessionStorage.getItem('maxPage'))==1) {
          (<HTMLInputElement>document.getElementById("btnNextPage")).disabled = true;
        }
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    )
  }

  isAdmin(): boolean {
    if (sessionStorage.getItem('roles') === "[ROLE_ADMIN]") {
      return true;
    } else {
      return false;
    }
  }

  searchCheck(): void {
    this.namefield = (<HTMLInputElement>document.getElementById("inSearch")).value;
    if (this.namefield != "") {
      this.search = true;
      this.pageNow = 1;
      this.getCarsByName(this.pageNow, this.namefield);
    } else {
      this.search = false;
      this.pageNow = 1;
      this.getCars(this.pageNow);
    }
  }

  NextPage(): void {
    if (Number(sessionStorage.getItem('maxPage')) == this.pageNow + 1) {
      this.pageNow = this.pageNow + 1;
      if (this.search == false) {
        this.getCars(this.pageNow);
      } else {
        this.getCarsByName(this.pageNow, this.namefield);
      }
      (<HTMLInputElement>document.getElementById("btnNextPage")).disabled = true;
      (<HTMLInputElement>document.getElementById("btnPrevPage")).disabled = false;
    } else if (Number(sessionStorage.getItem('maxPage')) != this.pageNow + 1) {
      this.pageNow = this.pageNow + 1;
      if (this.search == false) {
        this.getCars(this.pageNow);
      } else {
        this.getCarsByName(this.pageNow, this.namefield);
      }
    }
  }

  PrevPage(): void {
    if (this.pageNow - 1 == 1 ) {
      this.pageNow = this.pageNow - 1;
      if (this.search == false) {
        this.getCars(this.pageNow);
      } else {
        this.getCarsByName(this.pageNow, this.namefield);
      }
      (<HTMLInputElement>document.getElementById("btnPrevPage")).disabled = true;
      (<HTMLInputElement>document.getElementById("btnNextPage")).disabled = false;
    } else if (this.pageNow - 1 != 1) {
      (<HTMLInputElement>document.getElementById("btnNextPage")).disabled = false;
      this.pageNow = this.pageNow - 1;
      if (this.search == false) {
        this.getCars(this.pageNow);
      } else {
        this.getCarsByName(this.pageNow, this.namefield);
      }
    }
  }

  PageReset(): void {
    this.getCars(1);
    this.pageNow = 1;
    this.search = false;
    (<HTMLInputElement>document.getElementById("btnNextPage")).disabled = false;
    (<HTMLInputElement>document.getElementById("btnPrevPage")).disabled = true;
    if(Number(sessionStorage.getItem('maxPage'))==1) {
      (<HTMLInputElement>document.getElementById("btnNextPage")).disabled = true;
    }
  }

  public onOpenModal(car: Car, mode: string): void {
    const container = document.getElementById('card-container');
    const button = document.createElement('button');
    button.type = 'button';
    button.style.display = 'none';
    button.setAttribute('data-toggle', 'modal');
    if (mode === 'add') {
      button.setAttribute('data-target', '#addCarModal');
    } else if (mode === 'edit') {
      this.editCar = car;
      button.setAttribute('data-target', '#editCarModal');
    } else if (mode === 'delete') {
      this.deleteCar = car;
      button.setAttribute('data-target', '#deleteCarModal');
    }
    container.appendChild(button);
    button.click();
  }

  public onAddCar(addForm: NgForm): void {
    document.getElementById("close-form-car-add").click();
    console.log(addForm.value);
    this.CarService.addCar(addForm.value).subscribe((resp: Car) => {
      addForm.reset();
      this.PageReset();
    },
      (error: HttpErrorResponse) => {
        alert(error.message);
        addForm.reset();
      });
  }

  public onUpdateCar(car: Car): void {
    document.getElementById("close-form-car-edit").click();
    this.CarService.updateCar(car).subscribe((resp: Car) => {
      this.PageReset();
    },
      (error: HttpErrorResponse) => {
        alert(error.message);
      })
  }

  public onDeleteCar(carId: number): void {
    document.getElementById("close-form-car-delete").click();
    this.CarService.deleteCar(carId).subscribe((resp: void) => {
      this.PageReset();
    },
      (error: HttpErrorResponse) => {
        alert(error.message);
      })
  }
}
