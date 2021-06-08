package com.example.TaxiServlet.service.user.encoder;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordEncoderImpl implements PasswordEncoder{
    @Override
    public String encode(String password) {
        return BCrypt.hashpw(password,BCrypt.gensalt());
    }

    @Override
    public boolean checkPassword(String password, String hash) {
        return BCrypt.checkpw(password,hash);
    }
}
