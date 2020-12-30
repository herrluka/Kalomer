package com.example.kalomer;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Age_Number_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_age__number_);

        Button ageNumButton = findViewById(R.id.AgeNumberButton);
        final android.widget.NumberPicker np = findViewById(R.id.numberPicker);


        np.setMinValue(1);
        np.setMaxValue(120);


//        np.setOnValueChangedListener(new android.widget.NumberPicker.OnValueChangeListener() {
//                                         @Override
//                                         public void onValueChange(android.widget.NumberPicker picker, int oldVal, int newVal) {
//                                             Toast.makeText(Age_Number_Activity.this,
//                                                     "selected number " + picker.getValue(), Toast.LENGTH_SHORT).show();
//                                         }
//                                     }
//        );

        ageNumButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), HeightActivity.class);
                intent.putExtra("age",np.getValue());
                startActivity(intent);
                finish();
                Toast.makeText(Age_Number_Activity.this, Integer.toString(np.getValue()), Toast.LENGTH_LONG).show();
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

