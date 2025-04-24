package com.pixelo.pixelo.businessLogic;

import com.pixelo.pixelo.DataBase.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class LoginLogic {
    @Autowired
    JWTToken jwtToken;
    public  ArrayList<String> getLoginToken(String userName, String password){
        ArrayList<String> login = Login.getLogin(userName,password);
        if(login != null ) {
            String token = jwtToken.getToken(userName);
            login.add(token);
        }
       return login;
    }


}
