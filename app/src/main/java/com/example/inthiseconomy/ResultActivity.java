package com.example.inthiseconomy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        // retrieve intent that was passed to this activity and obtain the value associated with the "EMI" key
        Intent intent = getIntent();
        String emi = intent.getStringExtra("EMI");
        // find the TextView to display the EMI result and set the result to the answer
        TextView tvEMI = findViewById(R.id.tvEMI);
        tvEMI.setText("$" + emi);
        // set a listener on the OnClick event of the Back button and have it finish this activity once the back button is clicked.
        Button btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> {
            finish();
        });
    }
}