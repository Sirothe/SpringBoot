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
  public pageNow:number;

  constructor(private ClientService:ClientService) { }

  ngOnInit(): void {
    this.getClients(1);
    this.pageNow=1;
  }

  public getClients(page:number):void {
    this.ClientService.getClients(page).subscribe(
      (response: Client[]) => {
        this.clients=response;
      },
      (error:HttpErrorResponse) => {
        alert(error.message);
      }
    )
  }

  NextPage():void {
    if(sessionStorage.getItem('maxPage')==String(this.pageNow+1)) {
      this.pageNow=this.pageNow+1;
      this.getClients(this.pageNow);
      (<HTMLInputElement> document.getElementById("btnNextPage")).disabled = true;
      (<HTMLInputElement> document.getElementById("btnPrevPage")).disabled = false;
    }
  }
  
  PrevPage():void {
    if(this.pageNow-1==1) {
      this.pageNow=this.pageNow-1;
      this.getClients(this.pageNow);
      (<HTMLInputElement> document.getElementById("btnPrevPage")).disabled = true;
      (<HTMLInputElement> document.getElementById("btnNextPage")).disabled = false;
    }
  }
}
