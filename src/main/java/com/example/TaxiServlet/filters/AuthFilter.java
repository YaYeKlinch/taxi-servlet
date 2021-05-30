package com.example.TaxiServlet.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = {"/cars/*", "/add-car","/active-cars" })
public class AuthFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();
        if ( session==null || session.getAttribute("LoggedUser")==null){
            response.sendRedirect("/login-page");
        }
        if(response.isCommitted()){
            return;
        }

        filterChain.doFilter(request, response);


    }

    @Override
    public void destroy() {

    }
}