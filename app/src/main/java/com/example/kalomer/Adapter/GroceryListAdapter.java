package com.example.kalomer.Adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kalomer.Data.Grocery;
import com.example.kalomer.R;

import java.util.List;

public class GroceryListAdapter extends RecyclerView.Adapter<GroceryListAdapter.ViewHolder> {

    private Context context;
    private List<Grocery> list;

    public GroceryListAdapter(Context context, List<Grocery> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_row,parent,false);

        return new GroceryListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        if(list != null){
            Grocery grocery = list.get(position);
            holder.textViewTitle.setText(grocery.getName());
            holder.textViewKcal.setText(Double.toString(grocery.getKcal()) + " kcal/100g");
            int id = this.context.getResources().getIdentifier(grocery.getImage(), "drawable", context.getPackageName());
            Drawable drawable = context.getResources().getDrawable(id, null);
            holder.imageView.setImageDrawable(drawable);
        }
    }

    @Override
    public int getItemCount() {
        if(list == null) {
            return 0;
        } else {
            return list.size();
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView textViewTitle;
        public TextView textViewKcal;
        public ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.titleTextView);
            textViewKcal = itemView.findViewById(R.id.kcalTextView);
            imageView = itemView.findViewById(R.id.mainPic);
        }
    }
}
