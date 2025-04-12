package com.pixelo.pixelo.APICaller;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Base64;

public class ImgBBUploader {
    public static void main(String[] args) {
//         Your ImgBB API Key
        String apiKey = "c566d8a130fb6cc14f4fa26b568e003f";

        // Image file path
        File imagePath = new File("C:/Users/kushw/Downloads/concept-art-sunset-city.jpg");

        try {
            String formateName = detectImageFormate.getFormate(imagePath);
            // Read image and convert to base64
            BufferedImage image =  ImageIO.read(imagePath);
            ByteArrayOutputStream bose = new ByteArrayOutputStream();
            ImageIO.write(image,formateName,bose);
            byte[] imageBytes = bose.toByteArray();
            String base64Image = Base64.getEncoder().encodeToString(imageBytes);
            String data = "key=" + URLEncoder.encode(apiKey, "UTF-8")
                    + "&image=" + URLEncoder.encode(base64Image, "UTF-8")
            + "&expiration=" + 300;

            // Setup connection
            URL url = new URL("https://api.imgbb.com/1/upload");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

            // Send POST data
            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = data.getBytes("UTF-8");
                os.write(input, 0, input.length);
            }

            // Read response
            StringBuilder response = new StringBuilder();
            try (BufferedReader br = new BufferedReader(
                    new InputStreamReader(conn.getInputStream(), "UTF-8"))) {
                String line;
                while ((line = br.readLine()) != null) {
                    response.append(line.trim());
                }
            }

            // Output response
            System.out.println("Upload response:");
            System.out.println(response.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
