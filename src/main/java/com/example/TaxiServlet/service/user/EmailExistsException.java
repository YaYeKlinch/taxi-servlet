package com.example.TaxiServlet.service.user;


public class EmailExistsException extends RuntimeException {

    public EmailExistsException(String message) {
        super(message);
    }
}

