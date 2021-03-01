import { HttpClient, HttpResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { Router } from '@angular/router';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  private apiServerUrl=environment.apiBaseUrl;

  checkoutForm = this.formBuilder.group({
    username:'',
    password:''
  });

  constructor(private http:HttpClient,private formBuilder:FormBuilder,private router:Router) { }

  ngOnInit(): void {
  }

  Login():void {
    this.http.post(`${this.apiServerUrl}/login`,
    {username:this.checkoutForm.get('username').value,password:this.checkoutForm.get('password').value},{observe: 'response'})
    .subscribe(resp => this.dataUpdate(resp));
    this.checkoutForm.reset();
    this.router.navigateByUrl('/home');
  }

  getToken():string {
    return sessionStorage.getItem('token');
  }

  dataUpdate(data:HttpResponse<Object>):void {
    sessionStorage.setItem('token',data.headers.get('Authorization'));
    sessionStorage.setItem('username',data.headers.get('Username'));
    sessionStorage.setItem('roles',data.headers.get('Roles'))
  }
}
