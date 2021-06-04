package com.example.TaxiServlet.entity.dto;

import com.example.TaxiServlet.entity.Car;

import java.io.Serializable;
import java.util.List;

public class FoundCarDto implements Serializable {
    private static final long serialVersionUID = 135450463435324L;

    List<Car> cars;
    boolean alternate;

    public FoundCarDto() {
    }

    public FoundCarDto(List<Car> cars, boolean alternate) {
        this.cars = cars;
        this.alternate = alternate;
    }

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    public boolean isAlternate() {
        return alternate;
    }

    public void setAlternate(boolean alternate) {
        this.alternate = alternate;
    }
}
