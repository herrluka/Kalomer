package com.example.kalomer.Data;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "Meal")
public class Meal {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int id;

    @NonNull
    @ColumnInfo(name = "Date")
    private String date;

    @NonNull
    @ColumnInfo(name = "sumOfKcal")
    private double sumOfKcal;

    @NonNull
    @ColumnInfo(name = "sumOfFat")
    private double sumOfFat;

    @NonNull
    @ColumnInfo(name = "sumOfCarbons")
    private double sumOfCarbons;

    @NonNull
    @ColumnInfo(name = "sumOfProteins")
    private double sumOfProteins;

    @NonNull
    @ColumnInfo(name = "typeOfMeal")
    private String typeOfMeal;

    @NonNull
    @ColumnInfo(name = "quantity")
    private Double quantity;

    @NonNull
    @ColumnInfo(name = "groceryName")
    private String groceryName;

    @NonNull
    @ColumnInfo(name = "mealPhoto")
    private String mealPhoto;

    public Meal(@NonNull String date, double sumOfKcal, double sumOfFat,double sumOfProteins, double sumOfCarbons, String typeOfMeal, double quantity, String groceryName, String mealPhoto) {
        this.date = date;
        this.sumOfKcal = sumOfKcal;
        this.sumOfProteins = sumOfProteins;
        this.sumOfFat = sumOfFat;
        this.sumOfCarbons = sumOfCarbons;
        this.typeOfMeal = typeOfMeal;
        this.quantity = quantity;
        this.groceryName = groceryName;
        this.mealPhoto = mealPhoto;
    }

    @NonNull
    public String getDate() {
        return date;
    }

    public void setDate(@NonNull String date) {
        this.date = date;
    }

    public double getSumOfKcal() {
        return sumOfKcal;
    }

    public void setSumOfKcal(double sumOfKcal) {
        this.sumOfKcal = sumOfKcal;
    }

    public double getSumOfFat() {
        return sumOfFat;
    }

    public void setSumOfFat(double sumOfFat) {
        this.sumOfFat = sumOfFat;
    }

    public double getSumOfCarbons() {
        return sumOfCarbons;
    }

    public void setSumOfCarbons(double sumOfCarbons) {
        this.sumOfCarbons = sumOfCarbons;
    }

    public String getTypeOfMeal() {
        return typeOfMeal;
    }

    public void setTypeOfMeal(String typeOfMeal) {
        this.typeOfMeal = typeOfMeal;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NonNull
    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(@NonNull Double quantity) {
        this.quantity = quantity;
    }

    @NonNull
    public String getGroceryName() {
        return groceryName;
    }

    public void setGroceryName(@NonNull String groceryName) {
        this.groceryName = groceryName;
    }

    @NonNull
    public String getMealPhoto() {
        return mealPhoto;
    }

    public void setMealPhoto(@NonNull String mealPhoto) {
        this.mealPhoto = mealPhoto;
    }

    public double getSumOfProteins() {
        return sumOfProteins;
    }

    public void setSumOfProteins(double sumOfProteins) {
        this.sumOfProteins = sumOfProteins;
    }
}
