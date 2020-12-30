package com.example.kalomer.Data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Dao
public interface DatabaseDao {
    @Insert
    void insertGrocery(Grocery grocery);

    @Query("SELECT * FROM GROCERY")
    List<Grocery> getAllGroceries();

    @Query("SELECT * FROM Grocery WHERE CategoryId = (:categoryId)")
    //LiveData<List<Grocery>> getAllGroceryEntities(int categoryId);
    List<Grocery> getAllGroceryEntities(int categoryId);

    @Query("SELECT * FROM GROCERY WHERE id = :id")
    Grocery getGroceryById(int id);

    @Query("SELECT * FROM GROCERY WHERE favourite = 1")
    List<Grocery> getFavouriteGroceries();

    @Query("DELETE FROM GROCERY WHERE id = :id")
    int removeGrocery(int id);

    @Query("UPDATE GROCERY SET favourite = :fav WHERE id = :id")
    int updateGroceryFavourite(int id, boolean fav);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertCategory(Category category);


    @Query("SELECT * FROM Category")
    List<Category> getAllCategoryEntities();
    //LiveData<List<Category>> getAllCategoryEntities();

    @Insert
    void insertMeal(Meal meal);

    @Query("SELECT * FROM Meal")
    LiveData<List<Meal>> getAllMealEntities();

    @Query("SELECT * FROM Meal WHERE Date = :date")
    List<Meal> getMeals(String date);

    @Query("SELECT * FROM Meal WHERE typeOfMeal = :typeOfMeal and Date = :date")
    List<Meal> getMealsTypeByDate(String typeOfMeal, String date);

    @Query("DELETE FROM Meal WHERE id = :id")
    int removeMeal(int id);

    @Insert
    void insertGroceryMeal(GroceryMeal groceryMeal);

    @Query("SELECT * FROM GroceryMeal")
    LiveData<List<GroceryMeal>> getAllGroceryMealEntities();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertUser(User user);

    @Query("SELECT * FROM User")
    User getUserData();

    @Query("SELECT Kcal FROM User")
    double getUserKcal();
}
