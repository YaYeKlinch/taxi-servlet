package com.example.TaxiServlet.entity.dto;

public class StatisticDto {
    String carName;
    double totalDistance;
    int totalCosts;

    public StatisticDto() {
    }

    public StatisticDto(String carName, double totalDistance, int totalCosts) {
        this.carName = carName;
        this.totalDistance = totalDistance;
        this.totalCosts = totalCosts;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public double getTotalDistance() {
        return totalDistance;
    }

    public void setTotalDistance(double totalDistance) {
        this.totalDistance = totalDistance;
    }

    public int getTotalCosts() {
        return totalCosts;
    }

    public void setTotalCosts(int totalCosts) {
        this.totalCosts = totalCosts;
    }
}
