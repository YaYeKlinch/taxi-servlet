package com.example.TaxiServlet.entity;

import java.time.LocalDateTime;
import java.util.Objects;

public class TaxiOrder {
    private Long id;
    private String departure;
    private String arrival;
    private LocalDateTime time;
    private long carId;
    private long userId;
    private long distance;
    private long costs;

    public TaxiOrder(){

    }
    public TaxiOrder(Long id) {
        this.id = id;
    }

    public TaxiOrder(Long id, String departure, String arrival, LocalDateTime time, long carId, long userId, long distance, long costs) {
        this.id = id;
        this.departure = departure;
        this.arrival = arrival;
        this.time = time;
        this.carId = carId;
        this.userId = userId;
        this.distance = distance;
        this.costs = costs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaxiOrder taxiOrder = (TaxiOrder) o;
        return Objects.equals(id, taxiOrder.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public long getCarId() {
        return carId;
    }

    public void setCarId(long carId) {
        this.carId = carId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getDistance() {
        return distance;
    }

    public void setDistance(long distance) {
        this.distance = distance;
    }

    public long getCosts() {
        return costs;
    }

    public void setCosts(long costs) {
        this.costs = costs;
    }
}
