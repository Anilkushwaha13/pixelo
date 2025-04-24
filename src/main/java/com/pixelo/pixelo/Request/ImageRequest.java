package com.pixelo.pixelo.Request;

import java.util.ArrayList;
import java.util.List;


public class ImageRequest {
    private List<String> images = new ArrayList<>();
    private String token;
    private String userName;

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
