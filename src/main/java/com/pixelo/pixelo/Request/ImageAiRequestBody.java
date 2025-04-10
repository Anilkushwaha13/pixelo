package com.pixelo.pixelo.Request;

public class ImageAiRequestBody {
    String message;
    String ImageData;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getImageData() {
        return ImageData;
    }

    public void setImageData(String imageData) {
        ImageData = imageData;
    }
}
