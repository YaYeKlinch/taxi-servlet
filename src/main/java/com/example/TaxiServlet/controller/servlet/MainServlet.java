package com.example.TaxiServlet.controller.servlet;

import com.example.TaxiServlet.controller.command.Command;
import com.example.TaxiServlet.controller.command.PostCommand;
import com.example.TaxiServlet.controller.command.RestCommand;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;

import static java.util.Objects.nonNull;

@WebServlet("/taxi/*")
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
        processPostRequest(req,resp,path);
    }

    private void processPostRequest(HttpServletRequest request, HttpServletResponse response, String path) throws ServletException, IOException {
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
        if(nonNull(command)) {
            request.getRequestDispatcher(command.execute(request)).forward(request, response);
            return;
        }

        command = urlToRestCommand.get(path);

        if(nonNull(command)) {
            PrintWriter out = response.getWriter();
            out.print(command.execute(request));
            out.flush();
        }
    }
}
