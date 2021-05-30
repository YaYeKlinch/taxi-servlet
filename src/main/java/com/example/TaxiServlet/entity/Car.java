package com.example.TaxiServlet.entity;

import com.example.TaxiServlet.entity.enums.CarStatus;
import com.example.TaxiServlet.entity.enums.CarType;

import java.util.Objects;

public class Car {
    private long id;
    private String name;
    private CarStatus carStatus;
    private CarType carType;
    private String photo;
    private boolean active;
    int capacity;

    public Car(long id) {
        this.id = id;
    }

    public  Car(){

    }
    public Car(long id, String name, CarStatus carStatus, CarType carType, String photo, boolean active , int capacity) {
        this.id = id;
        this.name = name;
        this.carStatus = carStatus;
        this.carType = carType;
        this.photo = photo;
        this.active = active;
        this.capacity = capacity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return id == car.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CarStatus getCarStatus() {
        return carStatus;
    }

    public void setCarStatus(CarStatus carStatus) {
        this.carStatus = carStatus;
    }

    public CarType getCarType() {
        return carType;
    }

    public void setCarType(CarType carType) {
        this.carType = carType;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
