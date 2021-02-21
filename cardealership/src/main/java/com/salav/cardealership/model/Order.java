package com.salav.cardealership.model;

import javax.persistence.*;

@Entity
@Table(name="orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id", nullable = false, updatable = false)
    private Long orderId;
    @Column(name = "car_id", nullable = false)
    private Long carBought;
    @Column(name = "car_name")
    private String name;
    @Column(name = "car_model")
    private String model;
    @Column(name = "client_id", nullable = false)
    private Long clientId;
    private String status;

    public Order() {
    }

    public Order(Long orderId, Long carBought, String name, String model, Long clientId, String status) {
        this.orderId = orderId;
        this.carBought = carBought;
        this.name = name;
        this.model = model;
        this.clientId = clientId;
        this.status = status;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getCarBought() {
        return carBought;
    }

    public void setCarBought(Long carBought) {
        this.carBought = carBought;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
