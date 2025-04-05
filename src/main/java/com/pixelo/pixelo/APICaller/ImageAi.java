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

public class ImageAi {
    static  final String invoke_url ="https://ai.api.nvidia.com/v1/genai/nvidia/consistory";
    static final String api_key ="Bearer "+"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";

    static ArrayList<String> getImage(String prompt, String imp, String style_prompt, ArrayList<String> scene, String Negative ){
        try {
            ArrayList<String> imgdata = new ArrayList<>();

            JSONObject playload = new JSONObject();
            playload.put("mode", "init");
            playload.put("subject_prompt", prompt.toLowerCase());
            playload.put("subject_tokens", new JSONArray().put("anime").put("character").put(imp.toLowerCase()));
            playload.put("subject_seed",  6174);  // Kaprekar’s Constant (6174)
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

            try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))){
                StringBuilder response = new StringBuilder();
                String inputLine;
                while ((inputLine = in.readLine())  !=null){
                    response.append(inputLine);
                }

                JSONObject data = new JSONObject(response.toString());
                if(data.has("artifacts")){
                    JSONArray artifacts = data.getJSONArray("artifacts");
                    for (int idx =0; idx<artifacts.length();idx++) {
                        JSONObject imageSDATA = artifacts.getJSONObject(idx);
                        imgdata.add(imageSDATA.getString("base64"));
                    }
                }
                else {
                    System.out.println("Error:No artifats was found ");
                }
            }
            return  imgdata;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }

    }
}
