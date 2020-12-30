package com.example.kalomer;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.kalomer.Data.KalomerDatabase;
import com.example.kalomer.Data.User;
import com.example.kalomer.Utility.Utility;

public class WeightActivity extends AppCompatActivity {

    private User user;
    private KalomerDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weight);
        database = KalomerDatabase.getDatabase(this);
        final EditText weightText = findViewById(R.id.weightEditText);
        final Button weighButton = findViewById(R.id.weightButton);

        final int age = getIntent().getIntExtra("age",0);
        final String gendre = getIntent().getStringExtra("gendre");
        final int height = getIntent().getIntExtra("height",0);
        Toast.makeText(this,age + " "+ gendre + " " + height,Toast.LENGTH_LONG).show();


        weighButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(weightText.length() == 0){
                    weightText.setError("Prazno polje");
                }
                else {
                    int weight = Integer.parseInt(weightText.getText().toString());
                    double kcal = Utility.calculateFormula(age,gendre,weight, height);
                    double fat = Utility.calculateFat(kcal);
                    double proteins = Utility.calculateProteins(weight);
                    double carbs = Utility.calculateCarbs(kcal);
                    user = new User(age,weight,gendre,height,kcal,proteins,fat,carbs);
                    database.daoEntity().insertUser(user);
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    finish();
                }
            }
        });
    }

    @Override
    public void onBackPressed(){
        AlertDialog.Builder alert = new AlertDialog.Builder(this);

        alert.setTitle("Upozorenje");
        alert.setMessage("Napuštanje strane može prouzrokovati gubitak unetih podataka. Da li ste sigurni da želite da napustite stranu?");
        alert.setPositiveButton("DA", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        alert.setNegativeButton("NE", null);
        alert.setIcon(android.R.drawable.ic_dialog_alert);
        alert.show();
    }
}
