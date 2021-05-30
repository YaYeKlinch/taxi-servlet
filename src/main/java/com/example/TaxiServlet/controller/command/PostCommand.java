package com.example.TaxiServlet.controller.command;

public interface PostCommand extends Command{
    boolean isError(String url);
}
