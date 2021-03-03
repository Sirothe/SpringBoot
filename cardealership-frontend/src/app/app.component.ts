import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{
  title = 'cardealership-frontend';

  ngOnInit(): void {
    sessionStorage.setItem('token',"");
  }

  isLoggedIn():boolean {
    if(sessionStorage.getItem('token') === "") {
      return false;
    } else {
      return true;
    }
  }

  LogOut():void {
    sessionStorage.setItem('token',"");
    sessionStorage.setItem('username',"");
    sessionStorage.setItem('roles',"");
  }
}
