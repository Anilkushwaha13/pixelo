package com.pixelo.pixelo.APICaller;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ImageAi2 {
    static  final String invoke_url ="https://ai.api.nvidia.com/v1/genai/nvidia/consistory";
    //    static final String api_key ="Bearer "+"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";
    static final String api_key ="Bearer "+"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";

    public static List<String> getImage(String prompt, ArrayList<String> imp, String style_prompt, ArrayList<String> scene, String Negative ){
        try {
            List<String> imgdata = new ArrayList<>();

            Random rand = new Random();

            JSONObject playload = new JSONObject();
            playload.put("text", "init");
            playload.put("subject_prompt", prompt.toLowerCase());
            playload.put("subject_tokens", new JSONArray().put(imp.get(0).toLowerCase()).put(imp.get(1).toLowerCase()).put(imp.get(2).toLowerCase()));
            playload.put("subject_seed",  rand.nextInt(10000));  // Kaprekar’s Constant (6174)
            playload.put("style_prompt", style_prompt.toLowerCase());
            playload.put("scene_prompt1", scene.get(0).toLowerCase());
            playload.put("scene_prompt2", scene.get(1).toLowerCase());
            playload.put("negative_prompt", Negative.toLowerCase());
            playload.put("cfg_scale", 5);
            playload.put("same_initial_noise", false);


            HttpURLConnection connection = (HttpURLConnection) new URL(invoke_url).openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Authorization", api_key);
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("Content-Type", "application/json"); // Add content type
            connection.setDoOutput(true);

            try(OutputStream os = connection.getOutputStream()) {
                byte[] input= playload.toString().getBytes(StandardCharsets.UTF_8);
                os.write(input, 0,input.length);
            }

            int resposeCode = connection.getResponseCode();
            if(resposeCode !=200){
                System.out.println("Error: Response Code"+resposeCode);

                try (BufferedReader errorReader = new BufferedReader(new InputStreamReader(connection.getErrorStream()))){
                    String errorLine;
                    StringBuilder errorResponse = new StringBuilder();
                    while ((errorLine = errorReader.readLine())  !=null){
                        errorResponse.append(errorLine);
                    }
                    System.out.println("Error response: "+errorResponse);
                }
                return null;
            }
            else {

                try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                    StringBuilder response = new StringBuilder();
                    String inputLine;
                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                    System.out.println(response);
                    JSONObject data = new JSONObject(response.toString());
                    if (data.has("artifacts")) {
                        JSONArray artifacts = data.getJSONArray("artifacts");
                        for (int idx = 0; idx < artifacts.length(); idx++) {
                            JSONObject imageSDATA = artifacts.getJSONObject(idx);
                            imgdata.add(imageSDATA.getString("base64"));
                        }
                    } else {
                        System.out.println("Error:No artifats was found ");
                    }
                }
                return imgdata;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }

    }
}
