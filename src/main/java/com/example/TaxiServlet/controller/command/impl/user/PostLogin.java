package com.example.TaxiServlet.controller.command.impl.user;



import com.example.TaxiServlet.controller.command.PostCommand;
import com.example.TaxiServlet.entity.User;
import com.example.TaxiServlet.service.user.UserService;
import com.example.TaxiServlet.service.user.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

public class PostLogin implements PostCommand {

    private final UserService userService = new UserServiceImpl();
    private static final String URL_ERROR = "/login.jsp";
    private static final String URL_SUCCESS = "/";
    @Override
    public String execute(HttpServletRequest request) {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        Optional<User> userOptional = userService.getUser(email);
        if(checkRegistered(email,password ,userOptional)){
            if(!userOptional.get().isActive()){
                request.setAttribute("UserIsBanned" , true);
                return URL_ERROR;
            }
            userOptional.ifPresent(user -> {
                request.getSession().setAttribute("LoggedUser",user);
            });
            request.setAttribute("logout" , false);
            return URL_SUCCESS;
        }
        request.setAttribute("UserEx" , true);
        return URL_ERROR;
    }

    public boolean checkRegistered(String username, String password , Optional<User> userOptional) {
        AtomicBoolean matches = new AtomicBoolean(false);
        userOptional.ifPresent(user -> {
            matches.set(password.equals(user.getPassword()));
        });
        return matches.get();
    }
    @Override
    public  boolean isError(String url){
        return url.equals(URL_ERROR);
    }
}