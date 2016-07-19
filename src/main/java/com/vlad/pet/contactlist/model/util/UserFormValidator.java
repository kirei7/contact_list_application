package com.vlad.pet.contactlist.model.util;

import com.vlad.pet.contactlist.model.user.UserForm;
import org.springframework.stereotype.Component;

@Component
public class UserFormValidator {
    private static final int MIN_NAME_LENGTH = 5;
    private static final int MAX_NAME_LENGTH = 16;
    private static final int MIN_PASSWORD_LENGTH = 5;
    private static final int MAX_PASSWORD_LENGTH = 16;

    public void isValid(UserForm userForm) {
        if (userForm.getNickName() == null | userForm.getPassword() == null)
            throw new IllegalArgumentException("Nickname or password cannot be null");
        int nameLength = userForm.getNickName().length();
        int passwordLength = userForm.getPassword().length();
        if (nameLength < MIN_NAME_LENGTH | nameLength > MAX_NAME_LENGTH)
            throw new IllegalArgumentException("Invalid nickname size, must be: [" + MIN_NAME_LENGTH + ";" + MAX_NAME_LENGTH + "]");
        if (passwordLength < MIN_PASSWORD_LENGTH | passwordLength > MAX_PASSWORD_LENGTH)
            throw new IllegalArgumentException("Invalid password size, must be: [" + MIN_PASSWORD_LENGTH + ";" + MAX_PASSWORD_LENGTH + "]");
    }
}
