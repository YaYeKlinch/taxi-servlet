package com.example.TaxiServlet.controller.command.impl.user;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.servlet.http.HttpServletRequest;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class GetRegisterTest {

    @Mock
    HttpServletRequest request;

    @InjectMocks
    GetRegister getRegister;

    @Test
    void testGetRegisterPage(){
        assertEquals("/register.jsp", getRegister.execute(request));
    }
}