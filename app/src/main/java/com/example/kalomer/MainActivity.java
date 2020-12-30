package com.example.kalomer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kalomer.Data.KalomerDatabase;
import com.example.kalomer.Data.Meal;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import me.itangqi.waveloadingview.WaveLoadingView;

public class MainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    //vedzba
    private KalomerDatabase db;
    private List<Meal> meals;
    private WaveLoadingView wlv;
    private double kcal;
    private LocalDate date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView calendarImageView = findViewById(R.id.calendarImageView);
        ImageView addFoodImageView = findViewById(R.id.addImageView);
        ImageView settingImageView = findViewById(R.id.settingsImageView);
        TextView previousDate = findViewById(R.id.previousDateButton);
        TextView nextDate = findViewById(R.id.nextDayButton);
        ImageView statisticsImageView = findViewById(R.id.statisticsImageView);

        db = KalomerDatabase.getDatabase(this);
        kcal =db.daoEntity().getUserKcal();





        //vezba
//
//        Category sutlidza = new Category();
//        sutlidza.setName("pig");
//        sutlidza.setImage("guda");
//        db.daoEntity().insertCategory(sutlidza);
//        Category corn = new Category();
//        corn.setName("corn");
//        corn.setImage("corn");
//        db.daoEntity().insertCategory(corn);
//        Category fruit= new Category();
//        fruit.setName("fruit");
//        fruit.setImage("fruit");
//        Category vegetables= new Category();
//        vegetables.setName("vegetables");
//        vegetables.setImage("vegetables");
//        Category bbq = new Category();
//        bbq.setName("bbq");
//        bbq.setImage("bbq");
//        db.daoEntity().insertCategory(fruit);
//        db.daoEntity().insertCategory(vegetables);
//        db.daoEntity().insertCategory(bbq);
//        Category fish = new Category();
//        fish.setName("fish");
//        fish.setImage("fish");
//        db.daoEntity().insertCategory(bbq);
//        db.daoEntity().insertCategory(corn);
//        db.daoEntity().insertCategory(fruit);
//        db.daoEntity().insertCategory(vegetables);
//        db.daoEntity().insertCategory(fish);
////        Meal monte = new Meal(new Date(2016,4,5),2,  2,2,"Makare");
////        db.daoEntity().insertMeal(monte);
////        Meal maradona = new Meal(new Date(2016,4,242),2,  2,2,"JAJA");
////        db.daoEntity().insertMeal(maradona);
////
//        Grocery grocery = new Grocery("Pastrmka",55,33,66,22,"pastrmka",1);
//        db.daoEntity().insertGrocery(grocery);
//        db.daoEntity().insertGrocery(grocery);
//        db.daoEntity().insertGrocery(grocery);
//        db.daoEntity().insertGrocery(grocery);
//        db.daoEntity().insertGrocery(grocery);
//        db.daoEntity().insertGrocery(grocery);
//        Grocery choko = new Grocery("Čokolada",120,22,55,66,"chocolate",1);
//        db.daoEntity().insertGrocery(choko);
//        db.daoEntity().insertGrocery(choko);
//        db.daoEntity().insertGrocery(choko);
//        db.daoEntity().insertGrocery(choko);
//        db.daoEntity().insertGrocery(choko);
//        db.daoEntity().insertGrocery(choko);
//        db.daoEntity().insertGrocery(choko);db.daoEntity().insertGrocery(choko);
//        db.daoEntity().insertGrocery(choko);
//        db.daoEntity().insertGrocery(choko);
//        db.daoEntity().insertGrocery(choko);
//        db.daoEntity().insertGrocery(choko);



        String currentTime = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date());
        wlv = findViewById(R.id.waveLoadingView);



//        wlv.setAmplitudeRatio(54);
        wlv.setTopTitle("kcal/dan");
        wlv.setTopTitleColor(Color.WHITE);
        //wlv.setBottomTitle(currentTime);
        wlv.setBottomTitleColor(Color.WHITE);

        settingImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);

                alert.setTitle("Promena podataka");
                alert.setMessage("Da li ste sigurni da želite da promenite lične podatke?");
                alert.setPositiveButton("DA", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(getApplicationContext(),Age_Number_Activity.class);
                        startActivity(intent);
                    }
                });
                alert.setNegativeButton("NE", null);
                alert.setIcon(android.R.drawable.ic_dialog_alert);
                alert.show();
            }
        });


        calendarImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });
        addFoodImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),CategoiresActivity.class));
            }
        });

        previousDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                date  = date.minusDays(1);
                changeListViewByDate();
                calculateData();
                Toast.makeText(getApplicationContext(),date.toString(),Toast.LENGTH_SHORT).show();
            }
        });

        nextDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                date  = date.plusDays(1);
                changeListViewByDate();
                calculateData();
                Toast.makeText(getApplicationContext(),date.toString(),Toast.LENGTH_SHORT).show();
            }
        });

        statisticsImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new MainStatisticsFragment(meals);
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.mainListFragment,fragment)
                        .commit();
            }
        });
    }

    private void showDatePickerDialog(){
        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                this, Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    @Override
    protected void onStart() {
        super.onStart();
        refreshList();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        LocalDate ld = LocalDate.now();
        date = LocalDate.of(year,month + 1,dayOfMonth);
        changeListViewByDate();
        calculateData();
    }

    private void changeListViewByDate(){
        meals = db.daoEntity().getMeals(date.toString());
        MainListFragment listFragment = new MainListFragment(meals);
        getSupportFragmentManager().beginTransaction().replace(R.id.mainListFragment,listFragment).commit();
    }

    private void calculateData(){
        double sum = 0;
        for(Meal meal : meals){
            sum+=meal.getSumOfKcal();
        }
        wlv.setCenterTitle((int)kcal + "-" + (int)sum + "=" + ((int)kcal-(int)sum));

        if(kcal - (int)sum < 0){
            wlv.setWaveColor(Color.RED);
            wlv.setProgressValue(100);
        } else {
            wlv.setProgressValue((int)(sum / kcal * 100 ));
            wlv.setWaveBgColor(R.color.colorAccent);
        }
    }

    public void refreshList(){
        date = LocalDate.now();
        meals = db.daoEntity().getMeals(date.toString());
        FragmentManager manager = getSupportFragmentManager();
        if(meals == null){
            Fragment statisticsFragment = new MainStatisticsFragment(meals);
            manager.beginTransaction().replace(R.id.mainListFragment,statisticsFragment).commit();
        } else {
            Fragment listFragment= new MainListFragment(meals);
            manager.beginTransaction().replace(R.id.mainListFragment,listFragment).commit();
        }
        calculateData();
    }
}

