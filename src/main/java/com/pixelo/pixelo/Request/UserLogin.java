package com.pixelo.pixelo.Request;

public class UserLogin {

    private String EmailOrNumber;
    private String password;

    public String getEmailOrNumber() {
        return EmailOrNumber;
    }

    public void setEmailOrNumber(String emailOrNumber) {
        EmailOrNumber = emailOrNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
