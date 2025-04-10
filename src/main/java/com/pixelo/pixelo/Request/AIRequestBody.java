package com.pixelo.pixelo.Request;

import java.util.ArrayList;

public class AIRequestBody {
    String prompt;
    ArrayList<String> imp = new ArrayList<>();
    String style_prompt;
    ArrayList<String> scene = new ArrayList<>();
    String Negative;

    public String getPrompt() {
        return prompt;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    public ArrayList<String> getImp() {
        return imp;
    }

    public void setImp(ArrayList<String> imp) {
        this.imp = imp;
    }

    public String getStyle_prompt() {
        return style_prompt;
    }

    public void setStyle_prompt(String style_prompt) {
        this.style_prompt = style_prompt;
    }

    public ArrayList<String> getScene() {
        return scene;
    }

    public void setScene(ArrayList<String> scene) {
        this.scene = scene;
    }

    public String getNegative() {
        return Negative;
    }

    public void setNegative(String negative) {
        Negative = negative;
    }
}
