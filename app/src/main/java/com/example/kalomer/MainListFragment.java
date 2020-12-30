package com.example.kalomer;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kalomer.Adapter.MainListAdapter;
import com.example.kalomer.Data.KalomerDatabase;
import com.example.kalomer.Data.Meal;

import java.util.List;


public class MainListFragment extends Fragment {

    RecyclerView listView;
    private List<Meal> meals;
    private MainListAdapter adapter;
    private KalomerDatabase database;

    public MainListFragment(List<Meal> meals) {
        this.meals = meals;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        database = KalomerDatabase.getDatabase(getContext());
        View view = inflater.inflate(R.layout.fragment_main_list,container,false);
        adapter = new MainListAdapter(getContext(), meals);
        listView = view.findViewById(R.id.mainRecyclerView);
        listView.setLayoutManager(new LinearLayoutManager(getContext()));
        listView.setHasFixedSize(true);
        new ItemTouchHelper(simpleCallback).attachToRecyclerView(listView);
        listView.setAdapter(adapter);
        return view;
    }

    ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                Meal helper = meals.get(viewHolder.getAdapterPosition());
                meals.remove(viewHolder.getAdapterPosition());
                adapter.notifyDataSetChanged();
                int i = database.daoEntity().removeMeal(helper.getId());
                Toast.makeText(getContext(),Integer.toString(i),Toast.LENGTH_SHORT).show();
                if(getActivity() instanceof MainActivity){
                    ((MainActivity)getActivity()).refreshList();
                }
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
