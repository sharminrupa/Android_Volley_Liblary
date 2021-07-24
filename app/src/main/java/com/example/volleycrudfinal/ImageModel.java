package com.example.volleycrudfinal;

public class ImageModel {
    private String id;
    private String imageUrl;

    public ImageModel() {
    }

    public ImageModel(String id , String imageUrl) {
        this.id = id;
        this.imageUrl = imageUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
