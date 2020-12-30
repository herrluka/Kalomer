package com.example.kalomer.Adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kalomer.Data.Meal;
import com.example.kalomer.Model.ListItem;
import com.example.kalomer.R;

import java.util.ArrayList;
import java.util.List;


public class MainListAdapter extends RecyclerView.Adapter<MainListAdapter.ViewHolder> {

    Context context;
    List<Meal> list;

    public MainListAdapter(Context context, List<Meal> list){
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_row,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if(list!= null){
            Meal meal = list.get(position);
            holder.title.setText(meal.getGroceryName());
            holder.kcal.setText(meal.getQuantity() + "g");
            int id = this.context.getResources().getIdentifier(meal.getMealPhoto(), "drawable", context.getPackageName());
            Drawable drawable = context.getResources().getDrawable(id, null);
            holder.mainPic.setImageDrawable(drawable);
        }
    }

    @Override
    public int getItemCount() {
        if(list ==  null){
            return 0;
        } else {
            return list.size();
        }
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView title;
        public TextView kcal;
        public ImageView mainPic;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.titleTextView);
            kcal = itemView.findViewById(R.id.kcalTextView);
            mainPic = itemView.findViewById(R.id.mainPic);
        }
    }
}
