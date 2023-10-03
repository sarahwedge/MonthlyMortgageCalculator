package com.example.assignmentone;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.lang.Math;
import java.text.DecimalFormat;

public class ResultActivity extends AppCompatActivity {

    private static final DecimalFormat df = new DecimalFormat("0.00"); //format object for the calculation result
    private Button home; //home button

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Bundle extras = getIntent().getExtras(); //retrieve the data sent from the MainActivity
        if (extras != null) { //if data was sent
            String principal = extras.getString("principalAmount"); //get the principal value entered using the key chosen in the main activity
            String interest = extras.getString("interestRate"); //get the interest rate
            String amortPeriod = extras.getString("period"); // //get the period
            double cost = calculatePayment(Double.parseDouble(principal), Double.parseDouble(interest), Double.parseDouble(amortPeriod)); //call the calculatePayment method and cast each string to a double
            TextView tv = (TextView) findViewById(R.id.result); //initialize the text field object to display the result in
            String costString = "$" + Double.toString(cost); // format the result string
            tv.setText(costString); // update the text field to show the result string

        }

        home = findViewById(R.id.home); //home button
        home.setOnClickListener(new View.OnClickListener() { //set on click listener
            @Override
            public void onClick(View view) {

                Intent intent = new Intent( getApplicationContext(), MainActivity.class);
                startActivity(intent);  //go back to the main activity
            }
        });
    }

    private double calculatePayment(double principal, double yearlyRatePercent, double period) { //performs calculation to find the result value
        double payment = 0; //result value
        double yearlyRate = yearlyRatePercent / 100; //yearly insurance rate
        double monthlyRate = (yearlyRate / 12); //convert yearly insurance rate to the monthly insurance rate
        final int n = 12; //number of months in a year

        payment = ( principal * monthlyRate) / (1 - Math.pow((1 + (monthlyRate)), (-n * period))); //calculate the result
        payment = Double.parseDouble(df.format(payment)); //format the result to only 2 decimal places
        return payment;
    }
}