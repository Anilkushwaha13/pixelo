package com.pixelo.pixelo.Controller;

import com.pixelo.pixelo.DataBase.DatabaseUpdate;
import com.pixelo.pixelo.ExceptionHandler.pixeloException;
import com.pixelo.pixelo.Request.UserLogin;
import com.pixelo.pixelo.Request.UserRequest;
import com.pixelo.pixelo.businessLogic.JWTToken;
import com.pixelo.pixelo.businessLogic.LoginLogic;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/signup")
public class SignUpController {
    @Autowired
    JWTToken tokenChecker;
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
    @PostMapping("/getUpdate")
    public ResponseEntity<?> getUpdate(@RequestParam String email, String name, HttpServletRequest req){

        String authHeader = req.getHeader("Authorization");
        if (authHeader ==null  || !authHeader.startsWith("Bearer ")){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        String token = authHeader.substring(7);

        if (tokenChecker.validateToken(email, token)) {
            Boolean registration = DatabaseUpdate.getUpdate(email, name);
            if (registration){
                return ResponseEntity.ok()
                        .header("Change","Successful")
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(registration);
            }else try {
                throw new pixeloException("Update failed");
            } catch (pixeloException e) {
                throw new RuntimeException(e);
            }
        }
        else {

            return ResponseEntity.badRequest()
                    .body(false);
        }


    }
}
