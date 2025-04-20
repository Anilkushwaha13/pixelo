package com.pixelo.pixelo.Controller;

import com.pixelo.pixelo.ImageToPdf.PdfMaker;
import com.pixelo.pixelo.Request.ImageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/pdf")
public class ImagePDfConroller {

@PostMapping("/make-pdf")
public ResponseEntity<?> generatePdf(@RequestBody ImageRequest request) {
    
    byte[] pdfByte = PdfMaker.makePdf(request.getImages());

    return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=test.pdf")
            .contentType(MediaType.APPLICATION_PDF)
            .body(pdfByte);
}

    }

