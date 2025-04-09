package com.pixelo.pixelo.Controller;


import com.pixelo.pixelo.APICaller.ImageAi;
import com.pixelo.pixelo.businessLogic.imageLogic;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class APIController {

    @GetMapping("")
    public static ResponseEntity<?> getAiImage(){

        List<String> convertImage = ImageAi.getImage();
        Map<String,List<String>> response = new HashMap<>();
        response.put("Images",convertImage);

        return ResponseEntity.ok()
                .header("X-Total-Images", String.valueOf(convertImage.size()))
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);

    }
}
