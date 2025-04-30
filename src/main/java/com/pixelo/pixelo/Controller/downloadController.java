package com.pixelo.pixelo.Controller;

import com.pixelo.pixelo.Request.ImageRequest;
import com.pixelo.pixelo.businessLogic.JWTToken;
import com.pixelo.pixelo.businessLogic.UpdateImage;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/get")
public class downloadController {
    @Autowired
    JWTToken tokenChecker;
    @GetMapping("/download")
    public ResponseEntity<?> getDownload(@RequestParam String email, HttpServletRequest req) {
        String authHeader = req.getHeader("Authorization");
        if (authHeader ==null  || !authHeader.startsWith("Bearer ")){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        String token = authHeader.substring(7);
//        String token = request.getToken();

//        String userName = request.getUserName();
//        List<String> list = request.getImages();
//        System.out.println(list.get(0).length());
        if (tokenChecker.validateToken(email, token)) {
            return ResponseEntity.ok()
                    .body(true);
        }
        else {
//            Map<String,String> response = new HashMap<>();
//            response.put("Error","No User Found");
            return ResponseEntity.badRequest()
                    .body(false);
        }
    }
    @GetMapping("/ai-download")
    public ResponseEntity<?> getDownloadAndUpdate(@RequestBody ImageRequest request,@RequestParam String email, HttpServletRequest req) {
        String authHeader = req.getHeader("Authorization");
        if (authHeader ==null  || !authHeader.startsWith("Bearer ")){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        String token = authHeader.substring(7);
//        String token = request.getToken();
//        String userName = request.getUserName();

        if (tokenChecker.validateToken(email, token)) {
           boolean bol= UpdateImage.UpdateAiImage(email,request.getImages().get(0));
            return ResponseEntity.ok()
                    .body(bol);
        }

        else {
            return ResponseEntity.badRequest()
                    .body(false);
        }
    }
    @GetMapping("/ai-image")
    public ResponseEntity<?> getAiImage(@RequestParam int req) {

        List<String> list = UpdateImage.getAiImage(req);

            return ResponseEntity.ok()
                    .body(list);
        }
    }

