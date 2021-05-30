package com.example.TaxiServlet.controller.servlet;

import com.example.TaxiServlet.controller.command.Command;
import com.example.TaxiServlet.controller.command.PostCommand;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/")
public class MainServlet extends AbstractServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getRequestURI();
        path = path.replaceAll("\\d+/?","");
        processPageRequest(req,resp,path);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getRequestURI();
        path = path.replaceAll("\\d+/?","");
        processRestRequest(req,resp,path);
    }

    private void processRestRequest(HttpServletRequest request, HttpServletResponse response, String path) throws ServletException, IOException {
        PostCommand command = urlToPostCommand.get(path);
        String url = command.execute(request);
        if( command.isError(url)){
            request.getRequestDispatcher(url).forward(request,response);
        }
        else {
            response.sendRedirect(url);
        }
    }

    private void processPageRequest(HttpServletRequest request, HttpServletResponse response, String path) throws ServletException, IOException {
        Command command = urlToGetCommand.get(path);
        request.getRequestDispatcher(command.execute(request)).forward(request,response);
    };
}
