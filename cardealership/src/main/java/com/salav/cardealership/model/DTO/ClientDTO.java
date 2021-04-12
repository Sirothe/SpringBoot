package com.salav.cardealership.model.dto;

public class ClientDTO {
    private Long id;
    private String fullName;
    private String email;
    private String number;

    public ClientDTO() {
    }

    public ClientDTO(String fullName, String email, String number) {
        this.fullName = fullName;
        this.email = email;
        this.number = number;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
