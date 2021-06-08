package com.example.TaxiServlet.service.user;

import com.example.TaxiServlet.dao.DaoFactory;
import com.example.TaxiServlet.dao.user.UserDao;
import com.example.TaxiServlet.entity.User;
import com.example.TaxiServlet.entity.dto.UserDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {
    @Mock
    DaoFactory factory;

    @Mock
    UserDao dao;

    @Mock
    User user;

    @InjectMocks
    UserServiceImpl service;

    private final static long ID  = 1;
    private final static String EMAIL = "enail@emaple.com";
    private final static String NAME = "name";
    private final static String SURNAME = "surname";
    private final static String PASSWORD = "password";

    @BeforeEach
    void init() {
        when(factory.createUserDao()).thenReturn(dao);
    }


    @Test
    void shouldCreateUser_WhenEmailNotExists() {
        when(dao.findByUsername(eq(EMAIL))).thenReturn(Optional.empty());

        final UserDto toCreate = new UserDto();
        toCreate.setUsername(EMAIL);
        toCreate.setName(NAME);
        toCreate.setLastName(SURNAME);
        toCreate.setPassword(PASSWORD);

        final User userToCreate = new User();
        userToCreate.setUsername(EMAIL);
        userToCreate.setName(NAME);
        userToCreate.setLastName(SURNAME);
        userToCreate.setPassword(PASSWORD);

        when(dao.create(eq(userToCreate))).thenReturn(true);

        assertTrue(service.registerUser(toCreate));
    }

    @Test
    void shouldNotCreateUser_WhenEmailExists() {
        when(dao.findByUsername(eq(EMAIL))).thenReturn(Optional.of(user));

        UserDto toCreate = new UserDto();
        toCreate.setUsername(EMAIL);

        assertThrows(EmailExistsException.class, () -> service.registerUser(toCreate));
    }

    @Test
    void shouldReturnUserByEmail_WhenUserExists(){
        when(dao.findByUsername(eq(EMAIL))).thenReturn(Optional.of(user));
        assertSame(user, service.getUser(EMAIL).get());
    }

    @Test
    void shouldReturnUserById_WhenUserExists(){
        when(dao.findById(eq(ID))).thenReturn(Optional.of(user));
        assertSame(user, service.getUserById(ID).get());
    }

}