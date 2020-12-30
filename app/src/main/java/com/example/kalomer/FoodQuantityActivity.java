package com.example.kalomer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.Drawable;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;

import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kalomer.Data.Constantns;
import com.example.kalomer.Data.Grocery;
import com.example.kalomer.Data.GroceryMeal;
import com.example.kalomer.Data.KalomerDatabase;
import com.example.kalomer.Data.Meal;
import com.example.kalomer.Data.User;


import java.time.LocalDate;
import java.util.List;


public class FoodQuantityActivity extends AppCompatActivity {

    private KalomerDatabase db;
    private String typeOfMeal = Constantns.NORMAL;
    private Button moreOptions;
    private List<Meal> meals;
    private double mainProgress;
    private double userKcal;
    private EditText quantity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_quantity);

        moreOptions = findViewById(R.id.fqMoreOptionsButton);
        TextView title = findViewById(R.id.fqTitleTextView);
        TextView calories = findViewById(R.id.fqCaloriesPerGramsTextView);
        ImageView image = findViewById(R.id.fqCenterImage);
        TextView fat = findViewById(R.id.fqFatTextView);
        TextView carbons = findViewById(R.id.fqCarbonsTextView);
        TextView proteins = findViewById(R.id.fqProteinsTextView);
        Button add = findViewById(R.id.fqAddFoodButton);
        final ProgressBar progressBar = findViewById(R.id.mainProgressbar);
        quantity = findViewById(R.id.quantityEditText);


        db = KalomerDatabase.getDatabase(this);
        Intent intent = getIntent();
        final Grocery grocery = db.daoEntity().getGroceryById(intent.getIntExtra("id",0));
        title.setText(grocery.getName());
        calories.setText(Double.toString(grocery.getKcal()) + "kcal/100g");
        fat.setText(Double.toString(grocery.getFat()));
        carbons.setText(Double.toString(grocery.getCarbons()));
        proteins.setText(Double.toString(grocery.getProteins()));
        int id = getResources().getIdentifier(grocery.getImage(), "drawable", getPackageName());
        Drawable drawable = getResources().getDrawable(id, null);
        image.setImageDrawable(drawable);
        final LocalDate ld = LocalDate.now();
        User user = db.daoEntity().getUserData();
        meals = db.daoEntity().getMeals(ld.toString());
        userKcal = user.getKcal();
        double sumOfKcal =0;
        for(Meal m : meals){
            sumOfKcal += m.getSumOfKcal();
        }
         mainProgress = sumOfKcal / userKcal * 100;
        progressBar.setProgress((int)mainProgress);
        Toast.makeText(this,Double.toString(mainProgress),Toast.LENGTH_SHORT).show();
        if(mainProgress > 100){
            progressBar.setProgressTintList(ColorStateList.valueOf(Color.RED));
        }



        //Toast.makeText(this,ld.toString(),Toast.LENGTH_SHORT).show();

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(quantity.length() == 0 && !typeOfMeal.equals(Constantns.FAVOURITE)){
                    quantity.setError("Prazno polje");
                } else {
                    if (typeOfMeal != Constantns.FAVOURITE) {
                        double quantityNumber = Double.parseDouble(quantity.getText().toString());
                        Meal meal = new Meal(ld.toString(),
                                grocery.getKcal() / 100 * quantityNumber,
                                grocery.getFat() / 100 * quantityNumber,
                                grocery.getProteins() / 100 * quantityNumber,
                                grocery.getCarbons() / 100 * quantityNumber,
                                typeOfMeal,
                                quantityNumber,
                                grocery.getName(),
                                grocery.getImage());
                        db.daoEntity().insertMeal(meal);
                    } else {
                        db.daoEntity().updateGroceryFavourite( grocery.getId(),true);
                    }
                    Toast.makeText(getApplicationContext(), "Uspesno", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
        moreOptions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopup(v);
            }
        });

        quantity.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void afterTextChanged(Editable s) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try {
                    double quantityNumber = Double.parseDouble(quantity.getText().toString());
                    int result = (int) mainProgress + (int) (quantityNumber * grocery.getKcal() / userKcal);
                    progressBar.setProgress(result);
                    if(result > 100){
                        progressBar.setProgressTintList(ColorStateList.valueOf(Color.RED));
                    } else {
                        progressBar.setProgressTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorPrimary)));
                    }
                }
                catch (Exception e){
                    progressBar.setProgress((int)mainProgress);
                    Toast.makeText(getApplicationContext(),"USO",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }



    private void showPopup(View v){
        PopupMenu menu = new PopupMenu(this, v);
        menu.getMenu().add(Constantns.NORMAL);
        menu.getMenu().add(Constantns.BREAKFAST);
        menu.getMenu().add(Constantns.LUNCH);
        menu.getMenu().add(Constantns.DINNER);
        menu.getMenu().add(Constantns.FAVOURITE);
        menu.show();

        menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                typeOfMeal = item.getTitle().toString();
                if(typeOfMeal.equals(Constantns.FAVOURITE)){
                    quantity.setEnabled(false);
                } else
                    quantity.setEnabled(true);
                moreOptions.setText(item.getTitle());
                Toast.makeText(getApplicationContext(), item.getTitle().toString(), Toast.LENGTH_SHORT).show();
                return false;
            }
        });
    }
}
