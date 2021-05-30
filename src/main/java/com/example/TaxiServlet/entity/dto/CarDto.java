package com.example.TaxiServlet.entity.dto;

import com.example.TaxiServlet.entity.enums.CarType;

public class CarDto {
    String name;
    String photo;
    CarType carType;
    int capacity;
    public CarDto() {
    }

    public CarDto(String name, String photo, CarType carType , int capacity) {
        this.name = name;
        this.photo = photo;
        this.carType = carType;
        this.capacity= capacity;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public CarType getCarType() {
        return carType;
    }

    public void setCarType(CarType carType) {
        this.carType = carType;
    }
}
