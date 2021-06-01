package com.example.TaxiServlet.entity.dto;

import com.example.TaxiServlet.entity.Car;
import com.example.TaxiServlet.entity.TaxiOrder;
import com.example.TaxiServlet.entity.User;

import java.time.LocalDateTime;

public class OrderCarUserDto {

    LocalDateTime time;
    String departure;
    String arrival;
    long costs;
    String username;
    String carName;

    public OrderCarUserDto() {
    }

    public OrderCarUserDto(LocalDateTime time, String departure, String arrival, long costs, String username, String carName) {
        this.time = time;
        this.departure = departure;
        this.arrival = arrival;
        this.costs = costs;
        this.username = username;
        this.carName = carName;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getArrival() {
        return arrival;
    }

    public void setArrival(String arrival) {
        this.arrival = arrival;
    }

    public long getCosts() {
        return costs;
    }

    public void setCosts(long costs) {
        this.costs = costs;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }
}
