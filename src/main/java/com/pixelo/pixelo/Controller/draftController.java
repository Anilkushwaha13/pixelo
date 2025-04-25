package com.pixelo.pixelo.Controller;

import com.pixelo.pixelo.Request.ImageRequest;
import com.pixelo.pixelo.businessLogic.JWTToken;
import com.pixelo.pixelo.businessLogic.draftImageUser;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<?> getDownloadAndUpdate(@RequestBody ImageRequest request) {
        String token = request.getToken();
        String userName = request.getUserName();
        List<String> list = request.getImages();

        if (tokenChecker.validateToken(request.getUserName(), request.getToken())) {
          boolean bol = draftImageUser.draft(userName,list.get(0));
            return ResponseEntity.ok()
                    .body(bol);
        }

        else {
            Map<String,String> response = new HashMap<>();
            response.put("Error","No User Found");
            return ResponseEntity.badRequest()
                    .body(response);
        }
    }
    @GetMapping("/image")
    public ResponseEntity<?> getAiImage(@RequestParam String userName,String token) {
        if (tokenChecker.validateToken(userName, token)) {
            List<String> list = draftImageUser.getDraft(userName);

            return ResponseEntity.ok()
                    .body(list);
        }
        else return ResponseEntity.noContent().build();
    }
}
