package com.example.TaxiServlet.exceptions;

public class JsonParseException extends RuntimeException{
    public  JsonParseException(String message) {
        super(message);
    }
}
