package com.example.kalomer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.kalomer.Adapter.CategoryItemsAdapter;
import com.example.kalomer.Data.Category;
import com.example.kalomer.Data.KalomerDatabase;

import java.util.List;

public class CategoiresActivity extends AppCompatActivity implements CategoryItemsAdapter.onCategoryListener {

    CategoryItemsAdapter adapter;
    RecyclerView recyclerView;
    private KalomerDatabase db;
    private List<Category> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);


        recyclerView = findViewById(R.id.categoriesRecycleView);
        adapter = new CategoryItemsAdapter(this,this);

//        viewModel = new CategoryViewModel(getApplication());
//        viewModel.getAllEntites().observe(this, new Observer<List<Category>>() {
//            @Override
//            public void onChanged(List<Category> categories) {
//                adapter.setData(categories);
//            }
//        });

        db = KalomerDatabase.getDatabase(getApplicationContext());

        list = db.daoEntity().getAllCategoryEntities();
        adapter.setData(list);
        recyclerView.setAdapter(adapter);
        recyclerView.setNestedScrollingEnabled(false);
        if(this.recyclerView.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        } else if (this.recyclerView.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        }
        recyclerView.setHasFixedSize(true);

        Button newMealButton = findViewById(R.id.addNewMealButton);
        ImageButton breakfastButton = findViewById(R.id.breakfastImageButton);
        ImageButton lunchButton = findViewById(R.id.lunchImageButton);
        ImageButton dinnerButton = findViewById(R.id.dinnerImageButton);
        ImageButton favouriteButton = findViewById(R.id.favoriteImageButton);

        favouriteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MealActivity.class);
                intent.putExtra("title", "Omiljeno");
                startActivity(intent);
            }
        });

        dinnerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MealActivity.class);
                intent.putExtra("title", "Večera");
                startActivity(intent);
            }
        });

        lunchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MealActivity.class);
                intent.putExtra("title", "Ručak");
                startActivity(intent);
            }
        });

        breakfastButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MealActivity.class);
                intent.putExtra("title", "Doručak");
                startActivity(intent);
            }
        });

        newMealButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),CustomFoodActivity.class));
            }
        });


        //adapter.setData(viewModel.getAllEntites());


    }

    @Override
    public void onCategoryClick(int position) {
        Category cat = list.get(position);
        Intent intent = new Intent(this, FoodActivity.class);
        intent.putExtra("title",cat.getName());
        intent.putExtra("id",cat.getId());
        startActivity(intent);

    }
}
