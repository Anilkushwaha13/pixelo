package com.pixelo.pixelo.Controller;

import com.pixelo.pixelo.Request.ImageDownSaveRequest;
import com.pixelo.pixelo.Request.ImageRequest;
import com.pixelo.pixelo.businessLogic.JWTToken;
import com.pixelo.pixelo.businessLogic.draftImageUser;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/draft")
public class draftController {
    @Autowired
    JWTToken tokenChecker;

    @PostMapping("/save")
    public ResponseEntity<?> getDownloadAndUpdate(@RequestBody ImageDownSaveRequest request,@RequestParam String email, HttpServletRequest req) {
        String authHeader = req.getHeader("Authorization");
        if (authHeader ==null  || !authHeader.startsWith("Bearer ")){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        String token = authHeader.substring(7);


        System.out.println(request.getImage());
        System.out.println(email);
        boolean bol = false;
        if (tokenChecker.validateToken( email, token)) {
             bol = draftImageUser.draft(email,request.getImage());

        }
        return ResponseEntity.ok()
                .body(bol);
    }

    @GetMapping("/image")
    public ResponseEntity<?> getAiImage(@RequestParam String email,HttpServletRequest req){
    String authHeader = req.getHeader("Authorization");
    if (authHeader ==null  || !authHeader.startsWith("Bearer ")){
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    String token = authHeader.substring(7);
        if (tokenChecker.validateToken(email,token )) {
            Map<Integer, String> list = draftImageUser.getDraft(email);

            return ResponseEntity.ok()
                    .body(list);
        }
        else return ResponseEntity.noContent().build();
    }
    @DeleteMapping("delete")
    public  ResponseEntity<?> deleteDraft(@RequestParam String email,int id,HttpServletRequest req){
        String authHeader = req.getHeader("Authorization");
        if (authHeader ==null  || !authHeader.startsWith("Bearer ")){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        String token = authHeader.substring(7);
        if (tokenChecker.validateToken(email,token )) {
            return ResponseEntity.ok()
                    .body(draftImageUser.deleteDraft(email,id));
        }
        else return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
    }
}
