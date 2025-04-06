package com.pixelo.pixelo.Controller;



import com.pixelo.pixelo.ImageToPdf.PdfMaker;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/pdf")
public class ImagePDfConroller {

@PostMapping("/make-pdf")
public ResponseEntity<byte[]> generatePdf(@RequestBody ImageRequest request) {
//    List<String> base64Images = request.getImages();
//
//    System.out.println("Images received: " + base64Images);
    byte[] pdfbyte = PdfMaker.makePdf(request.getImages());

    return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=test.pdf")
            .contentType(MediaType.APPLICATION_PDF)
            .body(pdfbyte);
}

    }

