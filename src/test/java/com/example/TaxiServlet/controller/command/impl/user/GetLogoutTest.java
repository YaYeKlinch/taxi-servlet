package com.example.TaxiServlet.controller.command.impl.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GetLogoutTest {

    @Mock
    HttpServletRequest request;

    @Mock
    HttpSession httpSession;

    @InjectMocks
    GetLogout getLogout;

    @BeforeEach
    void init() {
        when(request.getSession()).thenReturn(httpSession);
    }

    @Test
    void testGetLogout(){
        assertEquals("/taxi/login-page" ,getLogout.execute(request));
        verify(httpSession,times(1)).invalidate();
    }

}