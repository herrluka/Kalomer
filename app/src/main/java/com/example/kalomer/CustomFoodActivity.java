package com.example.kalomer;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kalomer.Data.Category;
import com.example.kalomer.Data.Grocery;
import com.example.kalomer.Data.KalomerDatabase;

import java.util.List;

public class CustomFoodActivity extends AppCompatActivity {

    KalomerDatabase database = KalomerDatabase.getDatabase(this);
    private String photo;
    private int category = 0;
    private TextView caloriesTextView;
    private ImageView chosenIcon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_food);

        Button finishAddingFoodButton = findViewById(R.id.finishAddingFoodButton);
        final EditText title = findViewById(R.id.customTitleEditText);
        final EditText kcal = findViewById(R.id.customKcalEditText);
        final EditText carbs = findViewById(R.id.customCarbsEditText);
        final EditText fat = findViewById(R.id.customFatEditText);
        final EditText proteins = findViewById(R.id.customProteinEditText);
        final LinearLayout icon = findViewById(R.id.addIconLayout);
        final LinearLayout categories = findViewById(R.id.openCategoriesPopupLinearLayout);
        caloriesTextView = findViewById(R.id.categoryTextView);
        chosenIcon = findViewById(R.id.chosenIcon);

        finishAddingFoodButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(title.length() ==  0){
                    title.setError("Prazno polje");
                }
                else if(kcal.length() == 0){
                    kcal.setError("Prazno polje");
                }
                else if (carbs.length() == 0){
                    carbs.setError("Prazno polje");
                }
                else if (fat.length() == 0){
                    fat.setError("Prazno polje");
                }
                else if (proteins.length() == 0){
                    proteins.setError("Prazno polje");
                }
                else if(photo == null){
                    Toast.makeText(getApplicationContext(),"Dodajte nalepnicu",Toast.LENGTH_SHORT).show();
                }
                else if(category == 0){
                    Toast.makeText(getApplicationContext(),"Izaberite kategoriju",Toast.LENGTH_SHORT).show();
                }
                else {
                    String titleString = title.getText().toString();
                    double kcalNumber= Double.parseDouble(kcal.getText().toString());
                    double fatNumber = Double.parseDouble(fat.getText().toString());
                    double carbsNumber = Double.parseDouble(carbs.getText().toString());
                    double proteinsNumber = Double.parseDouble(proteins.getText().toString());
                    Grocery grocery = new Grocery(titleString,
                            kcalNumber,carbsNumber,proteinsNumber,fatNumber
                            ,photo,category);
                    grocery.setFavourite(true);
                    database.daoEntity().insertGrocery(grocery);
                    finish();
                }
            }
        });

        categories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopup(v);
            }
        });
        icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(getApplicationContext(),ChooseIconActivity.class),1);
            }
        });
    }




    private void showPopup(View v){
        PopupMenu menu = new PopupMenu(this, v);
        List<Category> list = database.daoEntity().getAllCategoryEntities();
        for(Category cat : list){
            menu.getMenu().add(0,cat.getId(),0,cat.getName());
        }
        menu.show();
        menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Toast.makeText(getApplicationContext(),Integer.toString(item.getItemId()),Toast.LENGTH_SHORT).show();
                category = item.getItemId();
                caloriesTextView.setText(item.getTitle());
                return false;
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //request code
        if(resultCode == RESULT_OK){
            photo = data.getStringExtra("image");
            int id = getResources().getIdentifier(photo, "drawable", getPackageName());
            Drawable drawable = getResources().getDrawable(id, null);
           chosenIcon.setImageDrawable(drawable);
        }
    }
}
