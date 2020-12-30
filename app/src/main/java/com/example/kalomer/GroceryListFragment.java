package com.example.kalomer;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kalomer.Adapter.FoodItemsAdapter;
import com.example.kalomer.Adapter.GroceryListAdapter;
import com.example.kalomer.Data.Grocery;
import com.example.kalomer.Data.KalomerDatabase;

import java.util.List;

public class GroceryListFragment extends Fragment {

    private GroceryListAdapter adapter;
    private List<Grocery> list;
    private KalomerDatabase database;

    public GroceryListFragment(List<Grocery> list) {
        this.list = list;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_grocery_list, container,false);
        RecyclerView recyclerView = view.findViewById(R.id.groceryListRecyclerView);
        database = KalomerDatabase.getDatabase(getContext());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        new ItemTouchHelper(simpleCallback).attachToRecyclerView(recyclerView);
        adapter = new GroceryListAdapter(getContext(),list);
        if(list != null){
            recyclerView.setAdapter(adapter);
        }
        return view;
    }

    ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

            Grocery helper = list.get(viewHolder.getAdapterPosition());
            list.remove(viewHolder.getAdapterPosition());
            adapter.notifyDataSetChanged();
            database.daoEntity().updateGroceryFavourite(helper.getId(), false);
        }

        @Override
        public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
            View itemView = viewHolder.itemView;
            int id = getResources().getIdentifier("ic_delete_black_24dp","drawable",getContext().getPackageName());
            Drawable drawable = getResources().getDrawable(id,null);
            int  iconMargin = (int)((itemView.getHeight() - drawable.getIntrinsicHeight()) / 2);
            ColorDrawable swipeBackground = new ColorDrawable(Color.parseColor("#FF0000"));
            swipeBackground.setBounds(itemView.getRight() + (int)dX,itemView.getTop(),itemView.getRight(),itemView.getBottom());
            drawable.setBounds(itemView.getRight() - iconMargin - drawable.getIntrinsicHeight(),
                    itemView.getTop() + iconMargin,itemView.getRight() - iconMargin,
                    itemView.getBottom() - iconMargin);
            swipeBackground.draw(c);
            c.save();

            c.clipRect(itemView.getRight() + (int)dX,itemView.getTop(),itemView.getRight(),itemView.getBottom());

            drawable.draw(c);

            c.restore();
            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        }
    };
}
