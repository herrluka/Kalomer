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

public class FoodItemsAdapter extends RecyclerView.Adapter<FoodItemsAdapter.ViewHolder> {

    private Context context;
    private List<Grocery> listItems = null;
    private FoodItemsAdapter.onFoodListener onFoodListener;

    public FoodItemsAdapter(Context context, FoodItemsAdapter.onFoodListener onFoodListener){
        this.context = context;
        this.onFoodListener = onFoodListener;
    }

    public FoodItemsAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<Grocery> categories){
        listItems = categories;
    }
    @NonNull
    @Override
    public FoodItemsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.grid_categories_item,parent,false);
        return new FoodItemsAdapter.ViewHolder(view,onFoodListener);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodItemsAdapter.ViewHolder holder, int position) {

        if(listItems != null) {
            Grocery item = listItems.get(position);
            holder.title.setText(item.getName());
            int id = this.context.getResources().getIdentifier(item.getImage(), "drawable", context.getPackageName());
            Drawable drawable = context.getResources().getDrawable(id, null);
            holder.image.setImageDrawable(drawable);
        }

    }

    @Override
    public int getItemCount() {
        if(listItems == null){
            return 0;
        }
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView title;
        public ImageView image;
        FoodItemsAdapter.onFoodListener onFoodListener;

        public ViewHolder(@NonNull View itemView, FoodItemsAdapter.onFoodListener onFoodListener) {
            super(itemView);
            title = itemView.findViewById(R.id.categoriesTextView);
            image = itemView.findViewById(R.id.categoriesViewImage);
            this.onFoodListener = onFoodListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onFoodListener.onFoodClick(getAdapterPosition());
        }
    }

    public interface onFoodListener{
        void onFoodClick(int position);
    }
}
