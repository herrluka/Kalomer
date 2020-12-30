package com.example.kalomer.Model;

public class CategoryItem {
    private String title;
    private int Image;

    public CategoryItem(String title, int image) {
        this.title = title;
        Image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImage() {
        return Image;
    }

    public void setImage(int image) {
        Image = image;
    }
}
