package com.example.TaxiServlet.service.user;

import com.example.TaxiServlet.dao.DaoFactory;
import com.example.TaxiServlet.dao.user.UserDao;
import com.example.TaxiServlet.entity.User;
import com.example.TaxiServlet.entity.dto.UserDto;
import com.example.TaxiServlet.entity.enums.Role;

import java.util.Optional;

public class UserServiceImpl implements UserService{
    private final DaoFactory daoFactory = DaoFactory.getInstance();


    @Override
    public boolean registerUser(UserDto userDto) {
        try (UserDao userDao = daoFactory.createUserDao()) {
            if (emailExists(userDto.getUsername(),userDao)) {
                throw new EmailExistsException("There is an account with that email address:" + userDto.getUsername());
            }
            User userToCreate = new User();
            userToCreate.setName(userDto.getName());
            userToCreate.setLastName(userDto.getLastName());
            userToCreate.setUsername(userDto.getUsername());
            userToCreate.setPassword(userDto.getPassword());
            userToCreate.setRole(Role.USER);
            userToCreate.setActive(true);
            return userDao.create(userToCreate);
    }
    }

    @Override
    public Optional<User> getUser(String email) {
        try (UserDao userDao = daoFactory.createUserDao()) {
            return userDao.findByUsername(email);
        }
    }

    @Override
    public Optional<User> getUserById(long userId) {
        try (UserDao userDao = daoFactory.createUserDao()) {
            return userDao.findById(userId);
        }
    }

    private boolean emailExists(String email, UserDao userDao) {
        return userDao.findByUsername(email).isPresent();
    }
}
