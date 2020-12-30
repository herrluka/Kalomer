package com.example.kalomer.Data;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "User")
public class User {

    @PrimaryKey
    @NonNull
    private int id;

    @NonNull
    @ColumnInfo(name = "Age")
    private int age;

    @NonNull
    @ColumnInfo(name = "Weight")
    private int weight;

    @NonNull
    @ColumnInfo(name = "Height")
    private int height;

    @NonNull
    @ColumnInfo(name = "Kcal")
    private double kcal;

    @NonNull
    @ColumnInfo(name = "Proteins")
    private double proteins;

    @NonNull
    @ColumnInfo(name = "Fat")
    private double fat;

    @NonNull
    @ColumnInfo(name = "Carbs")
    private double carbs;

    @NonNull
    @ColumnInfo(name = "Gendre")
    private String gendre;

    public User(int age, int weight, String gendre,int height,double kcal, double proteins,double fat,double carbs) {
        this.id = 1;
        this.age = age;
        this.weight = weight;
        this.gendre = gendre;
        this.height = height;
        this.kcal = kcal;
        this.proteins = proteins;
        this.fat = fat;
        this.carbs = carbs;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getGendre() {
        return gendre;
    }

    public void setGendre(String gendre) {
        this.gendre= gendre;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public double getKcal() {
        return kcal;
    }

    public void setKcal(double kcal) {
        this.kcal = kcal;
    }

    public double getProteins() {
        return proteins;
    }

    public void setProteins(double proteins) {
        this.proteins = proteins;
    }

    public double getFat() {
        return fat;
    }

    public void setFat(double fat) {
        this.fat = fat;
    }

    public double getCarbs() {
        return carbs;
    }

    public void setCarbs(double carbs) {
        this.carbs = carbs;
    }
}
