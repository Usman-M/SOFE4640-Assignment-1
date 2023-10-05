package com.example.inthiseconomy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // create variables to hold reference views
        EditText etPrincipal = findViewById(R.id.etPrincipal);
        EditText etAmortization = findViewById(R.id.etAmortization);
        EditText etInterestRate = findViewById(R.id.etInterestRate);
        TextView tvError = findViewById(R.id.tvError);
        Button btnCalculate = findViewById(R.id.btnCalculate);

        // bind listener to onClick event to perform the calculation and navigate to ActivityResult
        btnCalculate.setOnClickListener(v -> {
            // set validation message as invisible
            tvError.setVisibility(View.INVISIBLE);
            // identify if the user has left any of the form fields blank
            if (etPrincipal.getText().toString().isEmpty() || etInterestRate.getText().toString().isEmpty() || etAmortization.getText().toString().isEmpty()) {
                // make the error visible to the user
                tvError.setVisibility(View.VISIBLE);
            }
            else {
                // get the necessary inputs for the calculation from the form field views
                double principal = Double.parseDouble(etPrincipal.getText().toString());
                double interest = Double.parseDouble(etInterestRate.getText().toString());
                int amortization = Integer.parseInt(etAmortization.getText().toString());

                // perform emi calculation
                double monthlyInterest = interest / 1200;
                double monthlyAmortization = amortization * 12;
                double emi = (principal * monthlyInterest * (double)Math.pow(1 + monthlyInterest, monthlyAmortization)) / (double)(Math.pow(1 + monthlyInterest, monthlyAmortization) - 1);

                // check if the emi is invalid or infinite, and display the error if it is
                if (Double.isNaN(emi) || Double.isInfinite(emi)) {
                    tvError.setVisibility(View.VISIBLE);
                } else {
                    // create an intent and put the calculation result as an extra within the intent before passing it on to the result_activity
                    Intent intent = new Intent(MainActivity.this, ResultActivity.class);
                    intent.putExtra("EMI", String.format("%.2f", emi));
                    startActivity(intent);
                }
            }
        });
    }
}