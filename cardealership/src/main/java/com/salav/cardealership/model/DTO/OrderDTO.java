package com.salav.cardealership.model.dto;


public class OrderDTO {
    private Long orderId;
    private CarDTO car;
    private ClientDTO client;
    private String status;

    public OrderDTO() {
    }

    public OrderDTO(CarDTO car, ClientDTO client, String status) {
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

    public CarDTO getCar() {
        return car;
    }

    public void setCar(CarDTO car) {
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
