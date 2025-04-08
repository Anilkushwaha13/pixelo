package com.pixelo.pixelo.Controller;

import com.pixelo.pixelo.businessLogic.DatabaseUpdate;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/User")
public class SignUpLoginRequest {
//edfdtg
    @PostMapping("/register")
    public ResponseEntity<?> getRegister(@RequestBody UserRequest request){
        System.out.println(request);
        System.out.println(request.getUserName());
        System.out.println(request.getEmail());
        System.out.println(request.getNumber());
        System.out.println(request.getPassword());
        Boolean registration = DatabaseUpdate.getRegister(request.getUserName(),request.getNumber(), request.getEmail(), request.getPassword());
        return ResponseEntity.ok()
                .header("Register","Successful")
                .contentType(MediaType.APPLICATION_JSON)
                .body(registration);
    }

}
