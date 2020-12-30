package com.example.kalomer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.kalomer.Data.KalomerDatabase;
import com.example.kalomer.Data.User;

import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends AppCompatActivity {

    private KalomerDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        database = KalomerDatabase.getDatabase(this);
        final Intent intent;
        User user = database.daoEntity().getUserData();
        if(user == null){
            intent = new Intent(this,LaunchActivity.class);
        } else {
            intent = new Intent(this,MainActivity.class);
        }

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                startActivity(intent);
            }
        },1500);
    }
}
