package com.pixelo.pixelo.Controller;

import java.util.ArrayList;

public class ImageRequestwithQualityType {
    private ArrayList<String> images = new ArrayList<>();
    private String qualityOrType ;

    public String getQualityOrType() {
        return qualityOrType;
    }

    public void setQualityOrType(String qualityOrType) {
        this.qualityOrType = qualityOrType;
    }



    public ArrayList<String> getImages() {
        return images;
    }

    public void setImages(ArrayList<String> images) {
        this.images = images;
    }
}
