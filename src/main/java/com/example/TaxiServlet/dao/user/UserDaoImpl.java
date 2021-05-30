package com.example.TaxiServlet.dao.user;

import com.example.TaxiServlet.dao.JDBCDao;
import com.example.TaxiServlet.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class UserDaoImpl extends JDBCDao<User> implements UserDao{

    private static final String FIND_USER_BY_USERNAME = "SELECT * FROM user WHERE username = ?";

    public UserDaoImpl(Connection connection) {
        super(
                connection,
                "INSERT INTO user(id,name, lastname, username, password, role,active) VALUES(default,?,?,?,?,?,?)",
                "SELECT * FROM user WHERE id = ?",
                "SELECT SQL_CALC_FOUND_ROWS * FROM user LIMIT ?,?",
                "SELECT * FROM user",
                "SELECT COUNT(*)FROM user",
                "COUNT(*)",
                "UPDATE user SET name = ?, lastname = ?, username = ?, password = ?, role = ?, active = ? WHERE id = ?",
                7,
                "DELETE FROM user WHERE id = ?",
                new UserMapper());
    }

    @Override
    protected long getId(User entity) {
        return entity.getId();
    }

    @Override
    protected void setId(User entity, long id) throws SQLException {
        entity.setId(id);
    }

    @Override
    protected void setEntityValues(PreparedStatement statement, User entity) throws SQLException {
        statement.setString(1, entity.getName());
        statement.setString(2, entity.getLastName());
        statement.setString(3, entity.getUsername());
        statement.setString(4, entity.getPassword());
        statement.setString(5, entity.getRole().name());
        statement.setBoolean(6, entity.isActive());
    }

    @Override
    public Optional<User> findByUsername(String username) {
        User entity = null;

        try (PreparedStatement statement = connection.prepareStatement(FIND_USER_BY_USERNAME)) {
            statement.setString(1, username);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                entity = extractEntity(result);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return Optional.ofNullable(entity);
    }
}
