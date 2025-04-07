package com.pixelo.pixelo.Controller;

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
//        List<String> base64Images = request.getImages();
//        System.out.println("Images received: " + base64Images);
//        System.out.println(base64Images.size());
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
    public ResponseEntity<List<String>> compressImageWithQuality(@RequestBody ImageRequestwithQualityType request){
        List<String> base64Images = request.getImages();
        System.out.println("Images received: " + base64Images);
        System.out.println(base64Images.size());
        System.out.println(request.getQualityOrType());
        List<String> compressedImageWithQuality1 = imageLogic.getCompressedImageWithQuality(request.getImages(),request.getQualityOrType());

        return ResponseEntity.ok()
                .header("X-Total-Images", String.valueOf(compressedImageWithQuality1.size()))
                .contentType(MediaType.APPLICATION_JSON)
                .body(compressedImageWithQuality1);
    }

    @PostMapping("/get-convert")
    public ResponseEntity<List<String>> convertImage(@RequestBody ImageRequestwithQualityType request){
        List<String> base64Images = request.getImages();
        System.out.println("Images received: " + base64Images);
        System.out.println(base64Images.size());
        System.out.println(request.getQualityOrType());
        List<String> convertImage = imageLogic.getConvert(request.getImages(),request.getQualityOrType());

        return ResponseEntity.ok()
                .header("X-Total-Images", String.valueOf(convertImage.size()))
                .contentType(MediaType.APPLICATION_JSON)
                .body(convertImage);
    }


}
