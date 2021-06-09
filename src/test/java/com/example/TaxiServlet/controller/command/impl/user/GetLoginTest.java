package com.example.TaxiServlet.controller.command.impl.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.servlet.http.HttpServletRequest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class GetLoginTest {

    @Mock
    HttpServletRequest request;

    @InjectMocks
    GetLogin getLogin;

    @BeforeEach
    void init() {
    }

    @Test
    void shouldSetAttributeLogout_whenGetLoginExecutes() {
        assertEquals("/login.jsp", getLogin.execute(request));
        verify(request, times(1)).setAttribute(eq("logout"), eq(true));
    }
}