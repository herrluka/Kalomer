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

import com.example.kalomer.Data.Category;
import com.example.kalomer.R;

import java.util.List;


public class CategoryItemsAdapter extends RecyclerView.Adapter<CategoryItemsAdapter.ViewHolder> {

    private Context context;
    private List<Category> listItems = null;
    private onCategoryListener onCategoryListener;

    public CategoryItemsAdapter(Context context, onCategoryListener onCategoryListener){
        this.context = context;
        this.onCategoryListener = onCategoryListener;
    }

    public void setData(List<Category> categories){
        listItems = categories;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.grid_categories_item,parent,false);
        return new ViewHolder(view,onCategoryListener);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryItemsAdapter.ViewHolder holder, int position) {

        if(listItems != null) {
            Category item = listItems.get(position);
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
        onCategoryListener onCategoryListener;

        public ViewHolder(@NonNull View itemView,onCategoryListener onCategoryListener) {
            super(itemView);
            title = itemView.findViewById(R.id.categoriesTextView);
            image = itemView.findViewById(R.id.categoriesViewImage);
            this.onCategoryListener = onCategoryListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onCategoryListener.onCategoryClick(getAdapterPosition());
        }
    }

    public interface onCategoryListener{
        void onCategoryClick(int position);
    }
}
