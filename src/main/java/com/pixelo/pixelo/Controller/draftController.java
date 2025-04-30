package com.pixelo.pixelo.Controller;

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
    public ResponseEntity<?> getDownloadAndUpdate(@RequestBody ImageRequest request, HttpServletRequest req) {
        String authHeader = req.getHeader("Authorization");
        if (authHeader ==null  || !authHeader.startsWith("Bearer ")){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        String token = authHeader.substring(7);

        List<String> list = request.getImages();
        boolean bol = false;
        if (tokenChecker.validateToken(request.getEmail(), token)) {
             bol = draftImageUser.draft(request.getEmail(),list.get(0));

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
            List<String> list = draftImageUser.getDraft(email);

            return ResponseEntity.ok()
                    .body(list);
        }
        else return ResponseEntity.noContent().build();
    }
}
