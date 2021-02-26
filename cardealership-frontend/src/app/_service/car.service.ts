import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Car } from '../model/car';

@Injectable({
  providedIn: 'root'
})
export class CarService {
  private apiServerUrl=environment.apiBaseUrl;

  constructor(private http: HttpClient) { }

  public getCars(): Observable<Car[]> {
    return this.http.get<Car[]>(`${this.apiServerUrl}/car/all`);
  }

  public addCar(car: Car): Observable<Car> {
    return this.http.post<Car>(`${this.apiServerUrl}/car/add`, car);
  }

  public updateCar(car: Car): Observable<Car> {
    return this.http.put<Car>(`${this.apiServerUrl}/car/update`, car);
  }

  public deleteCar(carID: number): Observable<void> {
    return this.http.delete<void>(`${this.apiServerUrl}/car/delete/${carID}`);
  }
}
