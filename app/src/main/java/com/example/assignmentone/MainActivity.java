package com.example.assignmentone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    //define objects
    private Spinner spinnerIR, spinnerAP;
    private Button calculateButton;
    private EditText principalAmount;
    private static final String[] interestRates = {"6.54", "6.91", "7.1", "7.84", "7.94", "8"}; //set of interest rates
    private static final String[] periods = {"0.5", "2", "5", "10", "15", "20", "25", "30"}; //set of time periods

    private String selectedRate = "0"; //entered rate
    private String selectedPeriod = "0"; //entered period


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinnerIR = (Spinner)findViewById(R.id.interestRateSelect); //initialize the spinner IR object (IR = interest rate) to the interest rate spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_spinner_item,interestRates); //set the dropdown options to the interest array

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); //set the adapter to generic spinner dropdown item appearance
        spinnerIR.setAdapter(adapter); //link the adapter to the IR spinner
        spinnerIR.setOnItemSelectedListener(this); //recognize when an item is selected from the spinner dropdown options

        spinnerAP = (Spinner)findViewById(R.id.periodSelect); //initialize the spinner AP object (AP = amortization period) to the AP spinner
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_spinner_item,periods); //set the adapter to use the periods array as its option

        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); //set the default item appearance
        spinnerAP.setAdapter(adapter2); //link the adapter to the spinner AP object
        spinnerAP.setOnItemSelectedListener(this); //recognize when an item is selected from the spinner dropdown options

        principalAmount = findViewById(R.id.principalAmountInput); //initialize the principalAmount editText object

        calculateButton = findViewById(R.id.calculatePayment); //initialize the calculate button
        calculateButton.setOnClickListener(new View.OnClickListener() { //add onClick() listener to the calculate button
            @Override
            public void onClick(View view) {
                String principal = principalAmount.getText().toString(); //read the principal amount input value that the user entered
                Intent intent = new Intent( getApplicationContext(), ResultActivity.class); //create new intent for the ResultActivity
                //send the input data to the result activity
                intent.putExtra("principalAmount", principal); //put principal amount value. It can be accessed with the key: "principalAmount"
                intent.putExtra("interestRate", selectedRate);
                intent.putExtra("period", selectedPeriod);
                startActivity(intent); //start the Result activity on the click of the calculate button
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View v, int position, long id) { //method runs whenever an item was selected from a spinner dropdown
        int spin = parent.getId(); //obtain the id of the spinner that had an item selected
        if (spin == R.id.interestRateSelect) { //if active spinner is the interest rate spinner
            selectedRate = parent.getItemAtPosition(position).toString(); //get the currently selected dropdown item

        }
        else if (spin == R.id.periodSelect) { //if active spinner is the period spinner
            selectedPeriod = parent.getItemAtPosition(position).toString(); //get the currently selected dropdown item
            }

        else {

        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // TODO Auto-generated method stub
    }

}
