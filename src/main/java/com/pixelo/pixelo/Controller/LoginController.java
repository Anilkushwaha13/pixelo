package com.pixelo.pixelo.Controller;

import com.pixelo.pixelo.DataBase.DatabaseUpdate;
import com.pixelo.pixelo.DataBase.Login;
import com.pixelo.pixelo.Request.UserLogin;
import com.pixelo.pixelo.businessLogic.LoginLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    LoginLogic loginLogic;

    @PostMapping("/getlogin")
    public ResponseEntity<?> getLogin(@RequestBody UserLogin request){
        System.out.println(request);
        System.out.println(request.getEmailOrNumber());
        System.out.println(request.getPassword());

       ArrayList<String> loginList = loginLogic.getLoginToken(request.getEmailOrNumber(),request.getPassword());
        Map<String,String> response = new HashMap<>();
        if (loginList != null){
            response.put("Name",loginList.get(0));
            response.put("UserName",loginList.get(1));
            response.put("Token",loginList.get(2));
       } else {
            response.put("Error","No User Found");
        }

        return ResponseEntity.ok()
                .header("Register","Successful")
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }
}
