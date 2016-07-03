package com.vlad.pet.contactlist.model.exception;

public class UserAlreadyRegisteredException extends  RuntimeException{
    public UserAlreadyRegisteredException(String str) {
        super(str);
    }
}
