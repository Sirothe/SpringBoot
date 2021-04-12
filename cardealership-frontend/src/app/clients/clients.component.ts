import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Client } from '../model/client';
import { ClientService } from '../_service/client.service';

@Component({
  selector: 'app-clients',
  templateUrl: './clients.component.html',
  styleUrls: ['./clients.component.css']
})
export class ClientsComponent implements OnInit {
  public clients: Client[];
  public pageNow: number;
  public namefield: string;
  public search: boolean;
  public editClient: Client;
  public deleteClient: Client;

  constructor(private ClientService: ClientService) { }

  ngOnInit(): void {
    this.getClients(1);
    this.pageNow = 1;
    this.search = false;
  }

  public getClients(page: number): void {
    this.ClientService.getClients(page).subscribe(
      (response: Client[]) => {
        this.clients = response;
        if(Number(sessionStorage.getItem('maxPage'))==1) {
          (<HTMLInputElement>document.getElementById("btnNextPage")).disabled = true;
        }
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    )
  }

  public getClientsByName(page: number, name: string): void {
    this.ClientService.getClientsByName(page, name).subscribe(
      (response: Client[]) => {
        this.clients = response;
        if(Number(sessionStorage.getItem('maxPage'))==1) {
          (<HTMLInputElement>document.getElementById("btnNextPage")).disabled = true;
        }
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    )
  }

  searchCheck(): void {
    this.namefield = (<HTMLInputElement>document.getElementById("inSearch")).value;
    if (this.namefield != "") {
      this.search = true;
      this.pageNow = 1;
      this.getClientsByName(this.pageNow, this.namefield);
    } else {
      this.search = false;
      this.pageNow = 1;
      this.getClients(this.pageNow);
    }
  }

  NextPage(): void {
    if (Number(sessionStorage.getItem('maxPage')) == this.pageNow + 1) {
      this.pageNow = this.pageNow + 1;
      if (this.search == false) {
        this.getClients(this.pageNow);
      } else {
        this.getClientsByName(this.pageNow, this.namefield);
      }
      (<HTMLInputElement>document.getElementById("btnNextPage")).disabled = true;
      (<HTMLInputElement>document.getElementById("btnPrevPage")).disabled = false;
    } else if (Number(sessionStorage.getItem('maxPage')) != this.pageNow + 1) {
      this.pageNow = this.pageNow + 1;
      if (this.search == false) {
        this.getClients(this.pageNow);
      } else {
        this.getClientsByName(this.pageNow, this.namefield);
      }
    }
  }

  PrevPage(): void {
    if (this.pageNow - 1 == 1) {
      this.pageNow = this.pageNow - 1;
      if (this.search == false) {
        this.getClients(this.pageNow);
      } else {
        this.getClientsByName(this.pageNow, this.namefield);
      }
      (<HTMLInputElement>document.getElementById("btnPrevPage")).disabled = true;
      (<HTMLInputElement>document.getElementById("btnNextPage")).disabled = false;
    } else if (this.pageNow - 1 != 1) {
      (<HTMLInputElement>document.getElementById("btnNextPage")).disabled = false;
      this.pageNow = this.pageNow - 1;
      if (this.search == false) {
        this.getClients(this.pageNow);
      } else {
        this.getClientsByName(this.pageNow, this.namefield);
      }
    }
  }

  PageReset(): void {
    this.getClients(1);
    this.pageNow = 1;
    this.search = false;
    (<HTMLInputElement>document.getElementById("btnNextPage")).disabled = false;
    (<HTMLInputElement>document.getElementById("btnPrevPage")).disabled = true;
    if(Number(sessionStorage.getItem('maxPage'))==1) {
      (<HTMLInputElement>document.getElementById("btnNextPage")).disabled = true;
    }
  }

  public onOpenModal(client: Client, mode: string): void {
    const container = document.getElementById('container-buttons');
    const button = document.createElement('button');
    button.type = 'button';
    button.style.display = 'none';
    button.setAttribute('data-toggle', 'modal');
    if (mode === 'add') {
      button.setAttribute('data-target', '#addClientModal');
    } else if (mode === 'edit') {
      this.editClient = client;
      button.setAttribute('data-target', '#editClientModal');
    } else if (mode === 'delete') {
      this.deleteClient = client;
      button.setAttribute('data-target', '#deleteClientModal');
    }
    container.appendChild(button);
    button.click();
  }

  public onAddClient(addForm: NgForm): void {
    document.getElementById("close-form-client-add").click();
    console.log(addForm.value);
    this.ClientService.addClient(addForm.value).subscribe(
      (resp: Client) => {
        this.PageReset();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    )
  }

  public onUpdateClient(client: Client): void {
    document.getElementById("close-form-client-edit").click();
    this.ClientService.updateClient(client).subscribe((resp: Client) => {
      this.PageReset();
    },
      (error: HttpErrorResponse) => {
        alert(error.message);
      })
  }

  public onDeleteClient(clientId: number): void {
    document.getElementById("close-form-client-delete").click();
    this.ClientService.deleteClient(clientId).subscribe((resp: void) => {
      this.PageReset();
    },
      (error: HttpErrorResponse) => {
        alert(error.message);
      })
  }
}
