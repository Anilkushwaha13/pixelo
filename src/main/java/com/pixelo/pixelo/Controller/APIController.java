package com.pixelo.pixelo.Controller;


import com.pixelo.pixelo.APICaller.ImageAi;
import com.pixelo.pixelo.APICaller.ImageReaderAi;
import com.pixelo.pixelo.Request.AIRequestBody;
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

    @GetMapping("/getImage")
    public static ResponseEntity<?> getAiImage(@RequestBody AIRequestBody request){

        List<String> image = ImageAi.getImage(request.getPrompt(),request.getImp(),request.getStyle_prompt(),request.getScene(),request.getNegative());
        Map<String,List<String>> response = new HashMap<>();
        response.put("Images",image);

        return ResponseEntity.ok()
                .header("X-Total-Images", String.valueOf(image.size()))
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }
    @PostMapping("/imagereader")
    public static ResponseEntity<?> getImageData(@RequestBody ImageAiRequestBody request){

        List<String> image = ImageReaderAi.getImageData(request.getImageData(), request.getMessage());
        Map<String,List<String>> response = new HashMap<>();
        response.put("Images",image);

        return ResponseEntity.ok()
                .header("X-Total-Images", String.valueOf(image.size()))
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }
}
