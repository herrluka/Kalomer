package com.example.kalomer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kalomer.Data.Constantns;
import com.example.kalomer.Data.Grocery;
import com.example.kalomer.Data.KalomerDatabase;
import com.example.kalomer.Data.Meal;

import java.time.LocalDate;
import java.util.List;

public class MealActivity extends AppCompatActivity {

    private KalomerDatabase database;
    private List<Meal> meals;
    private List<Grocery> groceries;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal);

        String title = getIntent().getStringExtra("title");
        database = KalomerDatabase.getDatabase(this);
        if(title.equals("Omiljeno")){
            groceries = database.daoEntity().getFavouriteGroceries();
        } else {
            meals = database.daoEntity().getMealsTypeByDate(title, LocalDate.now().toString());
        }

        TextView textView = findViewById(R.id.titleMealTextView);
        textView.setText(title);
        if(meals != null) {
            MainListFragment fragment = new MainListFragment(meals);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.mealsFragmentContainer, fragment)
                    .commit();
        }
        if(groceries != null){
            GroceryListFragment groceryListFragment = new GroceryListFragment(groceries);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.mealsFragmentContainer, groceryListFragment)
                    .commit();
        }

    }
}
