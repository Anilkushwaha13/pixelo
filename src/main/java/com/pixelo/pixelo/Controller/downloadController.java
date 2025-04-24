package com.pixelo.pixelo.Controller;

import com.pixelo.pixelo.Request.ImageRequest;
import com.pixelo.pixelo.businessLogic.JWTToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/download-img")
public class downloadController {
    @Autowired
    JWTToken tokenChecker;
    @GetMapping()
    public ResponseEntity<?> getDownload(@RequestBody ImageRequest request) {
        String token = request.getToken();
        String userName = request.getUserName();
        List<String> list = request.getImages();
        if (tokenChecker.validateToken(userName, token)) {
            return ResponseEntity.ok()
                    .body(list);
        }

        else {
            Map<String,String> response = new HashMap<>();
            response.put("Error","No User Found");
            return ResponseEntity.badRequest()
                    .body(response);
        }
    }
    @GetMapping("/ai")
    public ResponseEntity<?> getDownloadAndUpdate(@RequestBody ImageRequest request) {
        String token = request.getToken();
        String userName = request.getUserName();
        List<String> list = request.getImages();

        if (tokenChecker.validateToken(userName, token)) {
            return ResponseEntity.ok()
                    .body(list);
        }

        else {
            Map<String,String> response = new HashMap<>();
            response.put("Error","No User Found");
            return ResponseEntity.badRequest()
                    .body(response);
        }
    }
}
