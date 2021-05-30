package com.example.TaxiServlet.dao.user;

import com.example.TaxiServlet.dao.Mapper;
import com.example.TaxiServlet.entity.User;
import com.example.TaxiServlet.entity.enums.Role;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements Mapper<User> {
    @Override
    public User extractFromResultSet(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getLong("user.id"));
        user.setName(rs.getString("user.name"));
        user.setLastName(rs.getString("user.lastname"));
        user.setUsername(rs.getString("user.username"));
        user.setPassword(rs.getString("user.password"));
        user.setRole(Role.valueOf(rs.getString("user.role")));
        user.setActive(rs.getBoolean("user.active"));
        return user;
    }
}
