package com.example.kalomer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kalomer.Adapter.FoodItemsAdapter;
import com.example.kalomer.Data.Grocery;
import com.example.kalomer.Data.KalomerDatabase;

import java.util.List;

public class FoodActivity extends AppCompatActivity implements FoodItemsAdapter.onFoodListener{

    private FoodItemsAdapter adapter;
    private RecyclerView recyclerView;
    private List<Grocery> arrayList;
    private KalomerDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);
        TextView title = findViewById(R.id.foodTextView);
        Intent intent = getIntent();
        title.setText(intent.getStringExtra("title"));
        db = KalomerDatabase.getDatabase(this);
       // Toast.makeText(this,Integer.toString(intent.getIntExtra("id",0)),Toast.LENGTH_LONG).show();
        arrayList = db.daoEntity().getAllGroceryEntities(intent.getIntExtra("id",0));
        adapter = new FoodItemsAdapter(this,this);
        adapter.setData(arrayList);
        recyclerView = findViewById(R.id.foodRecycleView);
        recyclerView.setAdapter(adapter);

        if(this.recyclerView.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        } else if (this.recyclerView.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        }

        recyclerView.setHasFixedSize(true);


//        for(int i = 0;i < 10;i++){
//            CategoryItem item  = new CategoryItem("item" + (i+1),R.drawable.pie_pic);
//            arrayList.add(item);
//        }

//        adapter = new CategoryItemsAdapter(this,);
        //recyclerView.setAdapter(adapter);
    }

    @Override
    public void onFoodClick(int position) {
        Grocery grocery = arrayList.get(position);
        Intent intent = new Intent(this, FoodQuantityActivity.class);
        intent.putExtra("id",grocery.getId());
        startActivity(intent);
    }
}
