package com.example.TaxiServlet.filters;


import com.example.TaxiServlet.entity.User;
import com.example.TaxiServlet.entity.enums.Role;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = {"/add-car","/cars/*","/active-cars/change-status","/all-orders/*"})
public class AdminFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws  IOException, ServletException {
        if(servletResponse.isCommitted()){
            return;
        }
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();
        if(session != null){
            User user = (User)session.getAttribute("LoggedUser");
            if(user != null && user.getRole() == Role.USER){
                response.sendRedirect("/");
            }else{
                filterChain.doFilter(request, response);
            }
        }
        else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }
    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void destroy() {
    }
}