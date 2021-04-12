package com.salav.cardealership.model.dto;

import com.salav.cardealership.model.Car;

public class OrderDTO {
    private Long orderId;
    private Car car;
    private ClientDTO client;
    private String status;

    public OrderDTO() {
    }

    public OrderDTO(Car car, ClientDTO client, String status) {
        this.car = car;
        this.client = client;
        this.status = status;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public ClientDTO getClient() {
        return client;
    }

    public void setClient(ClientDTO client) {
        this.client = client;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
