package com.pixelo.pixelo.Controller;

import com.pixelo.pixelo.Request.ImageRequest;
import com.pixelo.pixelo.Request.ImageRequestwithQualityType;
import com.pixelo.pixelo.businessLogic.imageLogic;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/image")
public class imageOperationController {

    @PostMapping("/get-compressed")
    public ResponseEntity<Map<String, List<String>>> compressImage(@RequestBody ImageRequest request){
        List<String> compressedImage = imageLogic.getCompressedImage(request.getImages());
        System.out.println(compressedImage);

        Map<String,List<String>> response = new HashMap<>();
        response.put("Data",compressedImage);


        return ResponseEntity.ok()
                .header("X-Total-Images", String.valueOf(compressedImage.size()))
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }

    @PostMapping("/get-compressed-quality")
    public ResponseEntity<Map<String,List<String>>> compressImageWithQuality(@RequestBody ImageRequestwithQualityType request){
        List<String> compressedImageWithQuality1 = imageLogic.getCompressedImageWithQuality(request.getImages(),request.getQualityOrType());

        Map<String,List<String>> response = new HashMap<>();
        response.put("Images",compressedImageWithQuality1);

        return ResponseEntity.ok()
                .header("X-Total-Images", String.valueOf(compressedImageWithQuality1.size()))
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }

    @PostMapping("/get-convert")
    public ResponseEntity<List<String>> convertImage(@RequestBody ImageRequestwithQualityType request){
        List<String> convertImage = imageLogic.getConvert(request.getImages(),request.getQualityOrType());
        System.out.println(request.getQualityOrType());
        System.out.println(convertImage);
        return ResponseEntity.ok()
                .header("X-Total-Images", String.valueOf(convertImage.size()))
                .contentType(MediaType.APPLICATION_JSON)
                .body(convertImage);
    }


}
