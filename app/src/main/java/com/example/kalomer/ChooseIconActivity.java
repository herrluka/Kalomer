package com.example.kalomer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Window;
import android.widget.Adapter;

import com.example.kalomer.Adapter.IconsAdapter;
import com.example.kalomer.Data.Category;
import com.example.kalomer.Data.Grocery;
import com.example.kalomer.Data.KalomerDatabase;

import java.util.List;

public class ChooseIconActivity extends AppCompatActivity implements IconsAdapter.onIconListener {

    private List<Grocery> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_icon);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout((int)(width*0.8),(int)(height*0.8));


        KalomerDatabase db = KalomerDatabase.getDatabase(getApplicationContext());
        list = db.daoEntity().getAllGroceries();
        RecyclerView recyclerView = findViewById(R.id.iconsRecyclerView);
        IconsAdapter adapter = new IconsAdapter(list,getApplicationContext(),this);
        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(),4));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void onIconClick(int position) {
        Grocery grocery = list.get(position);
        Intent resultIntent = new Intent();
        resultIntent.putExtra("image",grocery.getImage());
        setResult(RESULT_OK,resultIntent);
        finish();
    }
}
