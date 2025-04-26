package com.pixelo.pixelo.businessLogic;

import com.pixelo.pixelo.DataBase.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class LoginLogic {
    @Autowired
    JWTToken jwtToken;

    @Autowired
    Login login;


    public   Map<String,String> getLoginToken(String userName, String password){
        Map<String,String> data =login.getLogin(userName);
        if(data != null ) {
            Boolean password1 = PasswordEncrypter.checkPassword(password, data.get("password"));
            if (!password1){
                return null;
            }else data.remove("password");
            String token = jwtToken.getToken(userName);
            data.put("token",token);
        }
       return data;
    }


}
