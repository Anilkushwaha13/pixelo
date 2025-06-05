package com.pixelo.pixelo.APICaller;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class ImageAi2 {
    static  final String invoke_url ="https://ai.api.nvidia.com/v1/genai/stabilityai/stable-diffusion-xl";
    //    static final String api_key ="Bearer "+"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";
    static final String api_key ="Bearer "+"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";

    public static String getImage2(String prompt){
        try {
            List<String> imgdata = new ArrayList<>();

            Random rand = new Random();

            Map<String, Object> content = new HashMap<>();
            content.put("text", prompt);
            content.put("weight", 1.0);  // send as a double

            Map<String, Object> content2 = new HashMap<>();
            content2.put("text", "");
            content2.put("weight", -1.0);  // send as a double

            JSONObject playload = new JSONObject();
            playload.put("text_prompts", new JSONArray().put(new JSONObject(content)).put(new JSONObject(content2)));
            playload.put("seed", rand.nextInt(10000));
            playload.put("sampler", "K_DPM_2_ANCESTRAL");
            playload.put("cfg_scale", 5);
            playload.put("steps", 50);

            System.out.println(playload);


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
//                        for (int idx = 0; idx < artifacts.length(); idx++) {
                        System.out.println(artifacts);
                            JSONObject imageSDATA = artifacts.getJSONObject(0);
                        System.out.println("data:"+" "+artifacts.getJSONObject(0).getString("base64"));
                            return  imageSDATA.getString("base64");
//                        }
                    } else {
                        System.out.println("Error:No artifats was found ");
                        return null;
                    }
                }
//                return imgdata;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }

    }

    public static void main(String[] args) {
        System.out.println(getImage2("virat kohli,ghibli art"));
    }
}
