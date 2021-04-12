import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Car } from '../model/car';

@Injectable({
  providedIn: 'root'
})
export class CarService {
  private apiServerUrl=environment.apiBaseUrl;

  constructor(private http: HttpClient) { }

  public getCars(pageNumber:number): Observable<Car[]> {
    this.http.get(`${this.apiServerUrl}/car/p=${pageNumber}`,{observe: 'response'})
    .subscribe(resp => this.headerSaver(resp))
    return this.http.get<Car[]>(`${this.apiServerUrl}/car/p=${pageNumber}`);
  }

  public getCarsbyName(pageNumber:number,name:string):Observable<Car[]> {
    this.http.get(`${this.apiServerUrl}/car/nm=${name}/p=${pageNumber}`,{observe: 'response'})
    .subscribe(resp => this.headerSaver(resp))
    return this.http.get<Car[]>(`${this.apiServerUrl}/car/nm=${name}/p=${pageNumber}`);
  }

  public getAllCarsByName(name:string):Observable<Car[]> {
     return this.http.get<Car[]>(`${this.apiServerUrl}/car/nm=${name}`);
  }

  public addCar(car: Car): Observable<Car> {
    return this.http.post<Car>(`${this.apiServerUrl}/car`, car);
  }

  public updateCar(car: Car): Observable<Car> {
    return this.http.put<Car>(`${this.apiServerUrl}/car`, car);
  }

  public deleteCar(carID: number): Observable<void> {
    return this.http.delete<void>(`${this.apiServerUrl}/car/${carID}`);
  }

  public headerSaver(data:HttpResponse<Object>):void {
    sessionStorage.setItem('maxPage',data.headers.get('pagemax'));
    sessionStorage.setItem('totalItems',data.headers.get('totalitems'));
  }
}
