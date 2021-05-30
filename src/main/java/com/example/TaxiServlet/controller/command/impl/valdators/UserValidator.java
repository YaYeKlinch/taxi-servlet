package com.example.TaxiServlet.controller.command.impl.valdators;


import com.example.TaxiServlet.entity.dto.UserDto;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ResourceBundle;

import static java.util.Objects.isNull;

public class UserValidator {
    public static boolean validateUser(HttpServletRequest request, UserDto dto) throws IOException {
        ResourceBundle regex = ResourceBundle.getBundle("regexp");
        boolean isFirstnameEmpty =  checkNotEmpty(dto.getName(),"nameEmpty",request);
        boolean isLastnameEmpty = checkNotEmpty(dto.getLastName(),"surnameEmpty",request);
        boolean isUsernameEmpty =  checkNotEmpty(dto.getUsername(),"emailEmpty",request);
        boolean isPasswordEmpty =  checkNotEmpty(dto.getPassword(),"passwordEmpty",request);
        boolean isFirstNameCorrect = matchesRegex(dto.getName(),regex.getString("pattern.name"),"nameWrong",request);
        boolean isLastNameCorrect =  matchesRegex(dto.getLastName(),regex.getString("pattern.name"),"surnameWrong",request);
        boolean isUserNameCorrect = matchesRegex(dto.getUsername(),regex.getString("pattern.email.regexp"),"emailWrong",request);
        boolean isPasswordMatching = passwordMatching(dto.getPassword(),dto.getConfirmedPassword(),"passwordsNotEqual",request);
        return isFirstNameCorrect && isLastNameCorrect && isUserNameCorrect
                && isLastnameEmpty && isFirstnameEmpty && isPasswordEmpty && isUsernameEmpty
                && isPasswordMatching;
    }

    private static boolean  checkNotEmpty(String param, String emptyAttribute, ServletRequest request){
        if(isNull(param) || param.isEmpty()){
            request.setAttribute(emptyAttribute,true);
            return false;
        }
        return true;
    }

    private  static boolean matchesRegex(String param, String regex ,String wrongRegexAttribute, ServletRequest request){
        if (!param.matches(regex)) {
            request.setAttribute(wrongRegexAttribute,true);
            return false;
        }
        return true;
    }
    private  static boolean passwordMatching(String password, String confirm, String attribute, ServletRequest request){
        if(!password.equals(confirm)){
            request.setAttribute(attribute,true);
            return false;
        }
        return  true;
    }
}