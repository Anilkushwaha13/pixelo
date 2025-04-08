package com.pixelo.pixelo.Controller;

import java.util.ArrayList;
import java.util.List;


public class ImageRequest {
    private List<String> images = new ArrayList<>();

    public ImageRequest() {

    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }
}
