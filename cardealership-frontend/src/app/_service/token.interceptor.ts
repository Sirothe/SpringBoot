import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor,
  HttpErrorResponse
} from '@angular/common/http';
import { empty, Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { Router } from '@angular/router';
import { LoginComponent } from '../login/login.component';

@Injectable()
export class TokenInterceptor implements HttpInterceptor {

  constructor(private router:Router,private auth:LoginComponent) {}

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    request=request.clone({setHeaders:{Authorization:`${this.auth.getToken()}`}})
    return next.handle(request).pipe(
      catchError(err => {
        if (err.status === 401) {
          this.router.navigateByUrl('/login');
        } else if (err.status === 403) {
          this.router.navigateByUrl('/forbidden');
        }
        return empty();
      })
    );
  }
}
