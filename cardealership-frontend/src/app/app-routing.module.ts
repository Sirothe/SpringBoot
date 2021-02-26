import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CarsComponent } from './cars/cars.component';
import { ClientsComponent } from './clients/clients.component';
import { ForbiddenComponent } from './forbidden/forbidden.component';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { OrdersComponent } from './orders/orders.component';

const routes: Routes = [
  { path: 'home' , component: HomeComponent},
  { path: 'cars', component: CarsComponent},
  { path: 'clients', component: ClientsComponent},
  { path: 'orders', component: OrdersComponent},
  { path: 'forbidden', component: ForbiddenComponent},
  { path: 'login', component: LoginComponent},
  { path: '' , redirectTo: 'home', pathMatch: 'full'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
