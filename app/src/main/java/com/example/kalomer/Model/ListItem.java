package com.example.kalomer.Model;

public class ListItem {

    private String title;
    private float kcal;

    public ListItem(String title, float kcal) {
        this.title = title;
        this.kcal = kcal;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public float getKcal() {
        return kcal;
    }

    public void setKcal(float kcal) {
        this.kcal = kcal;
    }


}
