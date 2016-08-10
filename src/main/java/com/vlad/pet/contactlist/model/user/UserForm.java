package com.vlad.pet.contactlist.model.user;

public class UserForm {
    private String nickName;
    private String password;

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public UserForm withNickName(String nickName) {
        this.setNickName(nickName);
        return this;
    }
    public UserForm withPassword(String password) {
        this.setPassword(password);
        return this;
    }
}
