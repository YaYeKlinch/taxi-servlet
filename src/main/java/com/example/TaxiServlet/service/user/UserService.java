package com.example.TaxiServlet.service.user;

import com.example.TaxiServlet.entity.User;
import com.example.TaxiServlet.entity.dto.UserDto;

import java.util.Optional;

public interface UserService {
    boolean registerUser(UserDto userDto);
    Optional< User> getUser(String email);
}
