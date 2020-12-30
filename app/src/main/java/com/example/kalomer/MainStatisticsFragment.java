package com.example.kalomer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.kalomer.Data.KalomerDatabase;
import com.example.kalomer.Data.Meal;
import com.example.kalomer.Data.User;

import org.w3c.dom.Text;

import java.util.List;

public class MainStatisticsFragment extends Fragment {

    private List<Meal> list;
    private KalomerDatabase database;
    public MainStatisticsFragment(List<Meal> list) {
        this.list = list;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_statistics,container,false);
        ProgressBar proteins = view.findViewById(R.id.dailyProteinsBar);
        ProgressBar carbs = view.findViewById(R.id.dailyCarbsBar);
        ProgressBar fat = view.findViewById(R.id.dailyFatBar);
        TextView proteinsText = view.findViewById(R.id.belowProteinsTextView);
        TextView carbsText = view.findViewById(R.id.belowCarbsTextView);
        TextView fatText = view.findViewById(R.id.belowFatTextView);
        database = KalomerDatabase.getDatabase(getContext());
        User user = database.daoEntity().getUserData();
        double sumOfCarbs = 0;
        double sumOfProteins = 0;
        double sumOfFat = 0;

        for(Meal meal : list){
            sumOfCarbs += meal.getSumOfCarbons();
            sumOfProteins += meal.getSumOfProteins();
            sumOfFat += meal.getSumOfFat();
        }

        proteins.setProgress((int)(sumOfProteins / user.getProteins() * 100));
        proteinsText.setText((int)sumOfProteins + "/" + (int)user.getProteins() + "g");
        carbs.setProgress((int)(sumOfCarbs / user.getCarbs() * 100));
        int as=(int)(sumOfCarbs / user.getCarbs() * 100);
        Toast.makeText(getContext(),Integer.toString(as),Toast.LENGTH_SHORT).show();
        carbsText.setText((int)sumOfCarbs + "/" + (int)user.getCarbs() + "g");
        fat.setProgress((int)(sumOfFat / user.getFat()* 100));
        fatText.setText((int)sumOfFat +"/" + (int)user.getFat() + "g");

        return view;
    }
}
