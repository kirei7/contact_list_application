package com.vlad.pet.contactlist.model.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class OwnPasswordEncoder {
    private  PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public  String encode(String password) {
        return passwordEncoder.encode(password);
    }
}
