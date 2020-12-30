package com.example.kalomer.Data;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "GroceryMeal",
        foreignKeys ={
        @ForeignKey(entity = Grocery.class,
                    parentColumns = "id",
                    childColumns = "groceryId",
                    onDelete = ForeignKey.CASCADE),
        @ForeignKey(entity = Meal.class,
                    parentColumns = "id",
                    childColumns = "mealId",
                    onDelete = ForeignKey.CASCADE)})
public class GroceryMeal {

    @NonNull
    @PrimaryKey(autoGenerate = true)
    private int id;

    @NonNull
    @ColumnInfo(name = "groceryId")
    private String groceryId;

    @NonNull
    @ColumnInfo(name = "mealId")
    private String mealId;

    @NonNull
    @ColumnInfo(name = "Quantity")
    private double quantity;

    public GroceryMeal(@NonNull String groceryId,@NonNull String mealId, double quantity) {

        this.groceryId = groceryId;
        this.mealId = mealId;
        this.quantity = quantity;
    }

    @NonNull
    public String getGroceryId() {
        return groceryId;
    }

    public void setGroceryId(@NonNull String groceryId) {
        this.groceryId = groceryId;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    @NonNull
    public String getMealId() {
        return mealId;
    }

    public void setMealId(@NonNull String mealId) {
        this.mealId = mealId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
