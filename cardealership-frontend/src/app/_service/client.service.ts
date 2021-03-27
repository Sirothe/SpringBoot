import { HttpClient, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Client } from '../model/client';

@Injectable({
  providedIn: 'root'
})
export class ClientService {
  private apiServerUrl=environment.apiBaseUrl;

  constructor(private http:HttpClient) { }

  public getClients(pageNumber:number): Observable<Client[]> {
    this.http.get<Client[]>(`${this.apiServerUrl}/client/p=${pageNumber}`,{observe: 'response'})
    .subscribe(resp => this.headerSaver(resp));
    return this.http.get<Client[]>(`${this.apiServerUrl}/client/p=${pageNumber}`);
  }

  public addClient(client: Client): Observable<Client> {
    return this.http.post<Client>(`${this.apiServerUrl}/client`, client);
  }

  public updateClient(client: Client): Observable<Client> {
    return this.http.put<Client>(`${this.apiServerUrl}/client/update`, client);
  }

  public deleteClient(clientID: number): Observable<void> {
    return this.http.delete<void>(`${this.apiServerUrl}/client/${clientID}`);
  }

  public headerSaver(data:HttpResponse<Object>):void {
    sessionStorage.setItem('maxPage',data.headers.get('pagemax'));
    sessionStorage.setItem('totalItems',data.headers.get('totalitems'));
  }
}
