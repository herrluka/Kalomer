package com.example.kalomer;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class GendreChoiceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gendre_choice);

        final RadioGroup radioGroup = findViewById(R.id.gendreRadioGroup);
        Button button = findViewById(R.id.gendreButton);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = radioGroup.getCheckedRadioButtonId();
                final RadioButton radioButton = findViewById(id);
                Intent intent = new Intent(getApplicationContext(),WeightActivity.class);
                intent.putExtra("height",getIntent().getIntExtra("height",0));
                intent.putExtra("age", getIntent().getIntExtra("age",0));
                intent.putExtra("gendre",radioButton.getText());
                startActivity(intent);
                finish();
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
