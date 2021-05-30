package com.example.TaxiServlet.dao.user;

import com.example.TaxiServlet.dao.GenericDao;
import com.example.TaxiServlet.entity.User;

import java.util.Optional;

public interface UserDao extends GenericDao<User> {

    Optional<User> findByUsername(String username);
}
