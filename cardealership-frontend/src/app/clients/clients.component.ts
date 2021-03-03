import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Client } from '../model/client';
import { ClientService } from '../_service/client.service';

@Component({
  selector: 'app-clients',
  templateUrl: './clients.component.html',
  styleUrls: ['./clients.component.css']
})
export class ClientsComponent implements OnInit {
  public clients:Client[];

  constructor(private ClientService:ClientService) { }

  ngOnInit(): void {
    this.getClients();
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
