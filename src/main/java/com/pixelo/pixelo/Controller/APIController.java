package com.pixelo.pixelo.Controller;


import com.pixelo.pixelo.APICaller.ImageAi;
import com.pixelo.pixelo.APICaller.ImageAi2;
import com.pixelo.pixelo.APICaller.ImageReaderAi;
import com.pixelo.pixelo.Request.AIRequestBody;
import com.pixelo.pixelo.Request.AiRequestBody2;
import com.pixelo.pixelo.Request.ImageAiRequestBody;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class APIController {

    @PostMapping("/getImage")
    public ResponseEntity<?> getAiImage(@RequestBody AiRequestBody2 request){

        List<String> image = ImageAi2.getImage2(request.getPrompt());

        return ResponseEntity.ok()
                .header("X-Total-Images", "1")
                .contentType(MediaType.APPLICATION_JSON)
                .body(image);
    }
    @GetMapping("/getImage2")
    public ResponseEntity<?> getAiImage2(@RequestBody AIRequestBody request){

        List<String> image = ImageAi.getImage(request.getPrompt(),request.getImp(), request.getStyle_prompt(), request.getScene(),request.getNegative());

        return ResponseEntity.ok()
                .header("X-Total-Images", "2")
                .contentType(MediaType.APPLICATION_JSON)
                .body(image);
    }
    @PostMapping("/imageReader")
    public ResponseEntity<?> getImageData(@RequestBody ImageAiRequestBody request){

        String image = ImageReaderAi.getImageData(request.getImageData(), request.getMessage());

        return ResponseEntity.ok()
                .header("X-Total-Images", "1")
                .contentType(MediaType.APPLICATION_JSON)
                .body(image);
    }
}
