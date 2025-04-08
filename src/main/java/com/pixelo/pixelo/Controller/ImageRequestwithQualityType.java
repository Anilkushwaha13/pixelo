package com.pixelo.pixelo.Controller;

import java.util.ArrayList;
import java.util.List;

public class ImageRequestwithQualityType {
    private List<String> images = new ArrayList<>() ;
    private String qualityOrType ;

    public String getQualityOrType() {
        return qualityOrType;
    }

    public void setQualityOrType(String qualityOrType) {
        this.qualityOrType = qualityOrType;
    }



    public List<String> getImages() {
        return images;
    }

    public void setImages(ArrayList<String> images) {
        this.images = images;
    }
}
