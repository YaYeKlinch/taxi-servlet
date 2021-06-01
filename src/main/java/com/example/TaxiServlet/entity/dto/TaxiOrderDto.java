package com.example.TaxiServlet.entity.dto;

import java.util.Random;

public class TaxiOrderDto {
    private static final double COST_PER_METER = 2;
    private static final double START_PRICE = 4000;
    private String departure;
    private String arrival;
    public double calculateDistance(){
        Random random = new Random();
        return random.nextInt(10000)+100;
    }
    public int calculateCosts(double distance){
        return (int) (START_PRICE + distance*COST_PER_METER);
    }

    public TaxiOrderDto() {
    }

    public TaxiOrderDto(String departure, String arrival) {
        this.departure = departure;
        this.arrival = arrival;
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
}
