package com.example.kalomer.Data;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "Grocery",
    foreignKeys = @ForeignKey(entity = Category.class,
    parentColumns = "id",
    childColumns = "CategoryId",
    onDelete = ForeignKey.CASCADE))
public class Grocery {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int id;

    @NonNull
    @ColumnInfo(name = "Name")
    private String name;

    @NonNull
    @ColumnInfo(name = "Calories")
    private double kcal;

    @NonNull
    @ColumnInfo(name = "Carbons")
    private double carbons;


    @NonNull
    @ColumnInfo(name = "Proteins")
    private double proteins;


    @NonNull
    @ColumnInfo(name = "Fat")
    private double fat;


    @NonNull
    @ColumnInfo(name = "Image")
    private String image;


    @NonNull
    @ColumnInfo(name = "CategoryId")
    private int categoryId;

    @ColumnInfo(name = "favourite")
    private boolean favourite;

    public Grocery(@NonNull String name, double kcal, double carbons, double proteins, double fat, @NonNull String image, int categoryId) {
        this.name = name;
        this.kcal = kcal;
        this.carbons = carbons;
        this.proteins = proteins;
        this.fat = fat;
        this.image = image;
        this.categoryId = categoryId;
    }


    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    public double getKcal() {
        return kcal;
    }

    public void setKcal(double kcal) {
        this.kcal = kcal;
    }

    public double getCarbons() {
        return carbons;
    }

    public void setCarbons(double carbons) {
        this.carbons = carbons;
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

    @NonNull
    public String getImage() {
        return image;
    }

    public void setImage(@NonNull String image) {
        this.image = image;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public boolean isFavourite() {
        return favourite;
    }

    public void setFavourite(boolean favourite) {
        this.favourite = favourite;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
