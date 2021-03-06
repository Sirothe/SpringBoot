import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{
  public username:string;

  ngOnInit(): void {
    sessionStorage.setItem('token',"");
    sessionStorage.setItem('roles',"");
  }

  isAdmin():boolean {
    if(sessionStorage.getItem('roles') === "[ROLE_ADMIN]") {
      return true;
    } else {
      return false;
    }
  }

  isLoggedIn():boolean {
    if(sessionStorage.getItem('token') === "") {
      return false;
    } else {
      this.username=sessionStorage.getItem('username');
      return true;
    }
  }

  LogOut():void {
    sessionStorage.setItem('token',"");
    sessionStorage.setItem('username',"");
    sessionStorage.setItem('roles',"");
  }
}
