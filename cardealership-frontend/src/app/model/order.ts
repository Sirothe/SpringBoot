import { Car } from "./car";
import { Client } from "./client";

export interface Order {
    orderId:number;
    car:Car;
    client:Client;
    status:string;
}