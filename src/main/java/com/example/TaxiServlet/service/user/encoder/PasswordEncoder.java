package com.example.TaxiServlet.service.user.encoder;

public interface PasswordEncoder {
    String encode(String password);
    boolean checkPassword(String password, String hash);

    static PasswordEncoder getInstance(){
        return new PasswordEncoderImpl();
    }
}
