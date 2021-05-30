package com.example.TaxiServlet.entity.dto;

public class UserDto {
    private String name;
    private String lastName;
    private String username;
    private String password;
    private String confirmedPassword;

    public UserDto(){

    }
    public UserDto(String name, String lastName, String username, String password, String confirmedPassword) {
        this.name = name;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.confirmedPassword = confirmedPassword;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmedPassword() {
        return confirmedPassword;
    }

    public void setConfirmedPassword(String confirmedPassword) {
        this.confirmedPassword = confirmedPassword;
    }
}
