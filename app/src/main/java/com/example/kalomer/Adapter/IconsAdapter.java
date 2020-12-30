package com.example.kalomer.Adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kalomer.Data.Grocery;
import com.example.kalomer.R;

import java.util.List;

public class IconsAdapter extends RecyclerView.Adapter<IconsAdapter.ViewHolder> {

    List<Grocery> list;
    Context context;
    private onIconListener onIconListener;

    public IconsAdapter(List<Grocery> list, Context context, onIconListener onIconListener) {
        this.list = list;
        this.context = context;
        this.onIconListener = onIconListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.grid_icons_item,parent,false);
        return new IconsAdapter.ViewHolder(view,onIconListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if(list != null){
            Grocery grocery = list.get(position);
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

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public ImageView imageView;
        onIconListener onIconListener;


        public ViewHolder(@NonNull View itemView,onIconListener onIconListener) {
            super(itemView);
            imageView = itemView.findViewById(R.id.iconImageView);
            this.onIconListener = onIconListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onIconListener.onIconClick(getAdapterPosition());
        }
    }

    public interface onIconListener{
        void onIconClick(int position);
    }
}
