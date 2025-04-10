package com.pixelo.pixelo.APICaller;

import org.json.JSONArray;
import org.json.JSONObject;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class ImageReaderAi {
    static  final String invoke_url ="https://integrate.api.nvidia.com/v1/chat/completions";
    //    static final String api_key ="Bearer "+"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";
    static final String api_key ="Bearer "+"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";
//    public static List<String> getImageData(String Image, String message ){
//        try {
//            List<String> imgdata = new ArrayList<>();
//
//            String content = message +"  <img src=/"+"data:image/png;base64,"+Image.split(",")[1]+"/>";
//
//            Map<String ,String> message1 = new HashMap<>();
//            message1.put("role","user");
//            message1.put("content",content);
//
//
//            JSONObject playload = new JSONObject();
//            playload.put("model", "meta/llama-3.2-11b-vision-instruct");
//            playload.put("messages", new JSONArray().put(message1));
//            playload.put("max_tokens", 512);
//            playload.put("temperature", 1.00);
//            playload.put("top_p", 1.00);
//            playload.put("stream", true);
////            System.out.println(playload);
//
//
//            HttpURLConnection connection = (HttpURLConnection) new URL(invoke_url).openConnection();
//            connection.setRequestMethod("POST");
//            connection.setRequestProperty("Authorization", api_key);
//            connection.setRequestProperty("Accept", "application/json");
//            connection.setRequestProperty("Content-Type", "application/json"); // Add content type
//            connection.setDoOutput(true);
//
//            try(OutputStream os = connection.getOutputStream()) {
//                byte[] input= playload.toString().getBytes(StandardCharsets.UTF_8);
//                os.write(input, 0,input.length);
//            }
//
//            int resposeCode = connection.getResponseCode();
//            if(resposeCode !=200){
//                System.out.println("Error: Response Code"+resposeCode);
//
//                try (BufferedReader errorReader = new BufferedReader(new InputStreamReader(connection.getErrorStream()))){
//                    String errorLine;
//                    StringBuilder errorResponse = new StringBuilder();
//                    while ((errorLine = errorReader.readLine())  !=null){
//                        errorResponse.append(errorLine);
//                    }
//                    System.out.println("Error response: "+errorResponse);
//                }
//                return null;
//            }
//            else {
//
//                try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
//                    StringBuilder response = new StringBuilder();
//                    String inputLine;
//                    while ((inputLine = in.readLine()) != null) {
//                        response.append(inputLine);
//                    }
//                    System.out.println("Raw response: " + response);
//
//                    JSONObject data = new JSONObject(response.toString());
//                    System.out.println(data);
//                    if (data.has("artifacts")) {
//                        JSONArray artifacts = data.getJSONArray("artifacts");
//                        for (int idx = 0; idx < artifacts.length(); idx++) {
//                            JSONObject imageSDATA = artifacts.getJSONObject(idx);
//                            imgdata.add(imageSDATA.getString("base64"));
//                        }
//                    } else {
//                        System.out.println("Error:No artifats was found ");
//                    }
//                }
//                return imgdata;
//            }
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//            return null;
//        }
//
//    }
public static List<String> getImageData(String Image, String message) {
    List<String> imgdata = new ArrayList<>();
    try {
        String content = message + " <img src=\"data:image/png;base64," + Image.split(",")[1] + "\" />";

        Map<String, String> message1 = new HashMap<>();
        message1.put("role", "user");
        message1.put("content", content);

        JSONObject payload = new JSONObject();
        payload.put("model", "meta/llama-3.2-11b-vision-instruct");
        payload.put("messages", new JSONArray().put(new JSONObject(message1)));
        payload.put("max_tokens", 512);
        payload.put("temperature", 1.00);
        payload.put("top_p", 1.00);
        payload.put("stream", true);

        HttpURLConnection connection = (HttpURLConnection) new URL(invoke_url).openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Authorization", api_key);
        connection.setRequestProperty("Accept", "application/json");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setDoOutput(true);

        try (OutputStream os = connection.getOutputStream()) {
            byte[] input = payload.toString().getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);
        }

        int responseCode = connection.getResponseCode();
        if (responseCode != 200) {
            System.out.println("Error: Response Code " + responseCode);
            try (BufferedReader errorReader = new BufferedReader(new InputStreamReader(connection.getErrorStream()))) {
                String errorLine;
                StringBuilder errorResponse = new StringBuilder();
                while ((errorLine = errorReader.readLine()) != null) {
                    errorResponse.append(errorLine);
                }
                System.out.println("Error response: " + errorResponse);
            }
            return null;
        } else {
            try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                String inputLine;
                StringBuilder finalContent = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    if (inputLine.trim().startsWith("data: ")) {
                        String jsonPart = inputLine.substring(6).trim(); // remove "data: "
                        if (jsonPart.equals("[DONE]")) break;

                        JSONObject chunk = new JSONObject(jsonPart);
                        JSONArray choices = chunk.getJSONArray("choices");
                        for (int i = 0; i < choices.length(); i++) {
                            JSONObject delta = choices.getJSONObject(i).optJSONObject("delta");
                            if (delta != null && delta.has("content")) {
                                finalContent.append(delta.getString("content"));
                            }
                        }
                    }
                }

                System.out.println("Final content from model:");
                System.out.println(finalContent.toString());

                // OPTIONAL: If model returns base64 images in its response, parse them here
                // e.g., if it's JSON with "artifacts": [{"base64": "..."}]
                try {
                    JSONObject responseJson = new JSONObject(finalContent.toString());
                    if (responseJson.has("artifacts")) {
                        JSONArray artifacts = responseJson.getJSONArray("artifacts");
                        for (int idx = 0; idx < artifacts.length(); idx++) {
                            JSONObject imageData = artifacts.getJSONObject(idx);
                            imgdata.add(imageData.getString("base64"));
                        }
                    }
                } catch (Exception ignore) {
                    // Not in JSON format or not returning base64 images
                }

                return imgdata;
            }
        }

    } catch (Exception e) {
        System.out.println("Exception: " + e.getMessage());
        e.printStackTrace();
        return null;
    }
}


//    public static void main(String[] args) {
//        try {
//            BufferedImage image = ImageIO.read(new File("C:/Users/kushw/Downloads/concept-art-sunset-city.jpg"));
//            ByteArrayOutputStream bose = new ByteArrayOutputStream();
//            ImageIO.write(image,"jpeg",bose);
//            byte[] bytes = bose.toByteArray();
//            String Image1 = Base64.getEncoder().encodeToString(bytes);
//            String encodedImage="data:image/"+"jpeg"+";base64,"+Image1;
//            List<String> string = getImageData(encodedImage,"what is this");
//            System.out.println(string);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }

    }

